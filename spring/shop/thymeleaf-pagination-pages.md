# Thymeleaf 페이징(Pagination) UI — 구조·그룹 계산·표현식 정리

Thymeleaf로 만든 **페이지 이동 UI**입니다. 화면에는 보통 다음과 같은 형태입니다.

- **이전** — 한 페이지 뒤로  
- **1 2 3 4 5** — 현재 그룹의 페이지 번호(클릭 시 해당 페이지로)  
- **다음** — 한 페이지 앞으로  

예: `Previous  1 2 3 4 5  Next`

이 문서는 **① 그룹 계산(`start`/`end`)의 목적과 전체 코드**, **② `th:with`·`items.number`·`maxPage`·예제 1~3·`end` 비교·전체 그림 표**, **③ 이전·번호·다음 UI**, **④ 0 기반 vs 1 기반·괄호 표기**까지 한곳에 정리합니다.

---

## 전체 구조 한눈에

```
[ 이전 버튼 ]  →  page(items.number - 1)  (첫 페이지면 비활성)
      ↓
[ 1 2 3 4 5 ]  →  #numbers.sequence(start, end), 클릭 시 page(page - 1)
      ↓
[ 다음 버튼 ]  →  page(items.number + 1)  (마지막이면 비활성)
```

데이터는 Spring Data **`Page`** 객체(예: 템플릿 변수 `items`)를 사용한다고 가정합니다.

---

## 프로젝트 예시: `itemMng.html`에 가까운 전체 코드

`maxPage`는 컨트롤러에서 `model.addAttribute("maxPage", 5)`처럼 넘긴다고 가정합니다.

```html
<div th:with="start=${(items.number/maxPage)*maxPage + 1},
              end=${(items.totalPages == 0) ? 1 : (start + (maxPage - 1) < items.totalPages ? start + (maxPage - 1) : items.totalPages)}">
    <ul class="pagination justify-content-center">
        <!-- 1. 이전 -->
        <li class="page-item" th:classappend="${items.first}?'disabled'">
            <a th:onclick="'javascript:page(' + ${items.number - 1} + ')'"
               class="page-link" aria-label="Previous">
                <span aria-hidden="true">Previous</span>
            </a>
        </li>

        <!-- 2. 페이지 번호 -->
        <li class="page-item"
            th:each="page : ${#numbers.sequence(start, end)}"
            th:classappend="${items.number eq (page - 1)} ? 'active' : ''">
            <a th:onclick="'javascript:page(' + ${page - 1} + ')'"
               th:inline="text" class="page-link">[[${page}]]</a>
        </li>

        <!-- 3. 다음 -->
        <li class="page-item" th:classappend="${items.last}?'disabled'">
            <a th:onclick="'javascript:page(' + ${items.number + 1} + ')'"
               class="page-link" aria-label="Next">
                <span aria-hidden="true">Next</span>
            </a>
        </li>
    </ul>
</div>
```

- `page(...)` 는 템플릿의 `<script>`에 정의한 JavaScript 함수(예: `location.href=...`)로, **인자는 0 기반 페이지 인덱스**를 넘깁니다.

---

## 페이지 번호 그룹 계산 — 무엇을 위한 코드인가

Thymeleaf에서 아래 계산은 **페이지 번호를 한 묶음씩**만 화면에 찍기 위한 것입니다. 쇼핑몰·게시판에서 흔히 보는 형태입니다.

```text
1 2 3 4 5   [다음]
```

즉, 전체 페이지가 많아도 **한 번에 `maxPage`개**(예: 5개)만 `1 2 3 4 5`처럼 보여 주고, 다음 구간에서는 `6 7 8 9 10`처럼 넘어가게 할 때 사용합니다.

**이 코드의 핵심 목적:** 페이지 번호를 **그룹으로 묶어서** 출력하는 것입니다.

---

## `th:with` 블록 상세 — `start` / `end` 한 줄씩 풀이

같은 `<div>` 안에서만 쓰이는 지역 변수 **`start`**, **`end`**를 정의합니다. 이후 `#numbers.sequence(start, end)`가 이 값을 사용합니다.

### 전체 코드 (가독용 줄바꿈)

```html
<div th:with="
  start=${(items.number/maxPage)*maxPage + 1},
  end=${(items.totalPages == 0)
          ? 1
          : (start + (maxPage - 1) < items.totalPages
              ? start + (maxPage - 1)
              : items.totalPages)}">
```

한 줄로 쓴 실무 예는 다음과 같습니다. 의미는 위와 같습니다.

```html
<div th:with="start=${(items.number/maxPage)*maxPage + 1},
              end=${(items.totalPages == 0) ? 1 : (start + (maxPage - 1) < items.totalPages ? start + (maxPage - 1) : items.totalPages)}">
```

`itemMng.html`에서는 `end=` 전체를 **바깥 괄호**로 한 번 더 감싼 형태일 수 있습니다(연산 순서·가독용). 의미는 동일합니다.

```html
end=(${(items.totalPages == 0) ? 1 : (start + (maxPage - 1) < items.totalPages ? start + (maxPage - 1) : items.totalPages)})
```

---

### 1) `th:with`가 하는 일

| 항목 | 설명 |
|------|------|
| 문법 | `th:with="이름1=값1, 이름2=값2"` — **쉼표**로 여러 변수를 선언합니다. |
| 범위 | 이 `<div>`의 **자식 노드**에서만 `start`, `end`를 참조할 수 있습니다. |
| 평가 순서 | Thymeleaf는 같은 `th:with` 안에서 **앞에서 선언한 변수**를 뒤 식에서 사용할 수 있게 평가합니다. 그래서 **`end` 식 안에서 `start`를 써도** 됩니다. |

---

### 1-보) 핵심 개념 먼저

#### `items.number` — “현재 페이지”가 아니라 **인덱스 (0부터)**

Spring Data `Page#getNumber()`는 **첫 페이지가 0**입니다.

| 실제(사용자 기준) 페이지 | `items.number` |
|-------------------------|-----------------|
| 1페이지 | `0` |
| 2페이지 | `1` |
| 3페이지 | `2` |

#### `maxPage` — 한 번에 보여 줄 **링크 개수**

예: `maxPage = 5`이면 한 묶음에 **`1 2 3 4 5`**처럼 **5개**씩만 끊어서 보여 줍니다. 다음 묶음은 `6 7 8 9 10` … 입니다.

---

### 2) `start` — `start=${(items.number/maxPage)*maxPage + 1}`

#### 쓰는 값

| 식별자 | 의미 |
|--------|------|
| `items` | Spring Data `Page<Item>` 같은 **페이지 결과** 객체. |
| `items.number` | **현재 페이지 인덱스**. 첫 페이지가 `0`, 두 번째가 `1`, … |
| `maxPage` | 컨트롤러 등에서 넘긴 **한 묶음에 보여 줄 페이지 링크 개수**(예: `5`). |

#### 수식의 뜻 (한 줄 요약)

> “`items.number`가 속한 **페이지 번호 그룹**에서, 사용자에게 보여 줄 **첫 페이지 번호(1 기반)** 를 구한다.”

#### 왜 `(items.number / maxPage) * maxPage + 1` 인가?

1. **`items.number / maxPage`**  
   - 둘 다 정수로 취급되면 **정수 나눗셈**(소수 버림)이 됩니다.  
   - 몫 = “지금 인덱스가 **몇 번째 그룹**(0부터 세는 그룹 인덱스)인가?”에 해당합니다.

2. **`(... ) * maxPage`**  
   - 그 그룹 **직전**까지의 페이지 인덱스 개수(0 기반 누적)에 가깝게 맞춥니다.

3. **`+ 1`**  
   - 화면에 찍는 번호는 **1부터** 시작하므로, 그룹의 **시작 페이지 번호(1 기반)** 로 바꿉니다.

#### 예시 조건: `maxPage = 5`일 때 페이지 그룹 구조

| 현재(사용자 기준) 페이지 구간 | 화면에 나올 번호 |
|------------------------------|-----------------|
| 1 ~ 5 | 1 2 3 4 5 |
| 6 ~ 10 | 6 7 8 9 10 |
| 11 ~ 15 | 11 12 13 14 15 |

#### 계산 과정 상세 (예제 1 ~ 3)

**예제 1 — 지금이 1페이지**, `items.number = 0`, `maxPage = 5`

```text
(0 / 5) * 5 + 1
```

- 정수 나눗셈: `0 / 5 = 0`  
- 결과: `0 * 5 + 1 = 1` → **`start = 1`**

**예제 2 — 지금이 3페이지**, `items.number = 2`, `maxPage = 5`

```text
(2 / 5) * 5 + 1
```

- 정수 나눗셈: `2 / 5 = 0` (몫 0)  
- 결과: `0 * 5 + 1 = 1` → **`start = 1`**  
- 즉, 아직 **1 ~ 5 그룹**에 있습니다.

**예제 3 — 지금이 6페이지**, `items.number = 5`, `maxPage = 5`  
(6페이지 → 인덱스는 **5**인 점에 주의)

```text
(5 / 5) * 5 + 1
```

- 정수 나눗셈: `5 / 5 = 1`  
- 결과: `1 * 5 + 1 = 6` → **`start = 6`**  
- 즉, **6 ~ 10 그룹**의 시작입니다.

#### 숫자로 따라가기 (`maxPage = 5` 고정)

| 사용자 기준 페이지 | `items.number` | `items.number / 5` (정수 몫) | `몫 * 5` | `+ 1` → **`start`** |
|--------------------|----------------|------------------------------|----------|---------------------|
| 1페이지 | 0 | 0 | 0 | **1** |
| 3페이지 | 2 | 0 | 0 | **1** |
| 5페이지 | 4 | 0 | 0 | **1** |
| 6페이지 | 5 | 1 | 5 | **6** |
| 10페이지 | 9 | 1 | 5 | **6** |
| 11페이지 | 10 | 2 | 10 | **11** |

즉, 인덱스 `0~4`는 번호 `1~5` 그룹, `5~9`는 `6~10` 그룹처럼 **5개 단위**로 끊깁니다.

#### 주의 (실무)

EL에서 **`items.number / maxPage`가 실수 나눗셈**이 되면 몫이 달라져 그룹이 깨질 수 있습니다. 그럴 땐 서버에서 `start`/`end`를 계산해 넘기거나, 정수 나눗션이 되도록 타입·리터럴을 맞춥니다.

---

### 3) `end` — 삼항 연산 두 겹

```text
end = (items.totalPages == 0)
        ? 1
        : ( start + (maxPage - 1) < items.totalPages
              ? start + (maxPage - 1)
              : items.totalPages )
```

#### 바깥 삼항: `totalPages == 0` 이면 `end = 1`

| 이유 | 설명 |
|------|------|
| 데이터 없음 | 전체 페이지가 `0`이면, `start`만으로는 `end`를 만들기 애매할 수 있습니다. |
| 방어 값 | `end = 1`로 두면 `#numbers.sequence(start, end)`가 **짧은 구간**으로라도 동작하게 맞추는 경우가 많습니다(프로젝트에 따라 `th:if`로 페이징 전체를 숨기기도 함). |

#### 안쪽 삼항: “그룹 끝 후보”와 “전체 페이지 수” 비교

먼저 **이 그룹만 놓고 보면** 끝 번호는 항상 아래 후보입니다.

```text
후보 = start + (maxPage - 1)
```

예: `start = 1`, `maxPage = 5` → 후보 = `1 + 4` = **5** → 화면에는 `1 2 3 4 5`.

하지만 **마지막 그룹**에서는 전체 페이지 `items.totalPages`보다 큰 번호를 찍으면 안 됩니다.

| 조건 | 선택되는 `end` |
|------|----------------|
| `후보 < items.totalPages` | 아직 다음 그룹 페이지가 더 있음 → **`후보`** 그대로 사용. |
| 그렇지 않음 (`후보 >= items.totalPages`) | 이번 그룹이 전체의 끝까지 포함 → **`items.totalPages`** 로 잘라 씀. |

#### 숫자 예시 (`maxPage = 5`)

- `totalPages = 12`, 현재 그룹 `start = 1` → 후보 `5`, `5 < 12` → **`end = 5`**.
- `totalPages = 7`, `start = 6` → 후보 `10`, `10 < 7`는 거짓 → **`end = 7`** (화면에는 `6 7`만).

#### “마지막 페이지”를 꼭 고려하는 이유

전체 페이지가 **7**인데, 그룹만 보면 `6 7 8 9 10`까지 찍고 싶어질 수 있습니다. 하지만 **존재하지 않는 페이지 번호**는 링크로 내면 안 되므로, 안쪽 삼항에서 **`start + (maxPage - 1)`** 과 **`items.totalPages`** 를 비교해 **더 작은 쪽을 `end`로** 택합니다.

- 위 예에서 후보 끝은 `10`이지만, 실제 마지막은 `7` → **`end = 7`** (출력은 `6 7`).

#### 전체 그림 (`maxPage = 5`, `totalPages`가 충분히 큰 경우)

아래 **`start` / `end`** 는 “이상적인 그룹 끝”이며, 실제로는 `totalPages`에 맞춰 잘립니다.

| 현재(사용자 기준) 페이지 | `start` | `end` | 출력(번호) |
|-------------------------|---------|-------|-------------|
| 1 | 1 | 5 | 1 2 3 4 5 |
| 3 | 1 | 5 | 1 2 3 4 5 |
| 6 | 6 | 10 | 6 7 8 9 10 |
| 11 | 11 | 15 | 11 12 13 14 15 |

#### 마지막 그룹 예외 예시

| `items.totalPages` | 이상적 후보 `start + (maxPage - 1)` | 실제 사용 `end` |
|--------------------|-------------------------------------|------------------|
| 7 | 10 (6~10 그룹을 가정할 때) | **7** |

#### 실제 화면 예시 (번호만)

- **1페이지 그룹:** `1 2 3 4 5`
- **6페이지 그룹:** `6 7 8 9 10` (단, `totalPages`가 7이면 `6 7`까지만)
- **11페이지 그룹:** `11 12 13 14 15`

#### 연산자 우선순위와 괄호

안쪽 조건은 실제로 다음과 같이 읽힙니다.

```text
( start + (maxPage - 1) ) < items.totalPages
```

즉 **`start + (maxPage - 1)` 먼저 계산**한 뒤, `items.totalPages`와 비교합니다. 괄호를 명시해 두면 `itemMng.html`처럼 **가독·IDE 파싱**에도 유리합니다.

---

### 4) `start`와 `end`가 붙은 뒤 템플릿에서 일어나는 일

1. `start`, `end`가 정해집니다.  
2. `th:each="page : ${#numbers.sequence(start, end)}"`에서 `page`가 `start`, `start+1`, …, `end`로 돕니다.  
3. 화면에는 **1 기반** 번호가 나열되고, `page(...)`에는 **`page - 1`** 같은 **0 기반 인덱스**를 넘깁니다.

---

### 5) 한 줄 요약 표

| 변수 | 식 (개념) | 목적 |
|------|-----------|------|
| `start` | `(items.number / maxPage) * maxPage + 1` | 현재 속한 **그룹의 첫 페이지 번호(1 기반)**. |
| `end` | `totalPages==0 ? 1 : (후보 < totalPages ? 후보 : totalPages)` | 그룹의 **끝 페이지 번호**, 단 **전체 페이지를 넘지 않게** 자름. |

---

## 1. 이전(Previous) 버튼

### 비활성: 첫 페이지일 때

```html
<li class="page-item" th:classappend="${items.first}?'disabled'">
```

| 의미 | 설명 |
|------|------|
| `items.first` | 지금이 **첫 페이지**이면 `true` |
| `th:classappend` | 조건이 참이면 기존 `class`에 **`disabled`** 추가 |
| 결과 | `<li class="page-item disabled">` → Bootstrap에서 클릭 막는 스타일 |

| 실제 페이지 | `items.first` |
|-------------|---------------|
| 1페이지 | `true` |
| 2페이지 이후 | `false` |

### 클릭: 이전 페이지(인덱스)로 이동

```html
<a th:onclick="'javascript:page(' + ${items.number - 1} + ')'" ...>
```

| 항목 | 설명 |
|------|------|
| `items.number` | **현재 페이지 인덱스 (0부터)** |
| `items.number - 1` | 한 칸 이전 인덱스 |

**예: 지금이 3페이지** → `items.number = 2` → `2 - 1 = 1` → `javascript:page(1)` → **2페이지**로 이동.

### 생성되는 느낌의 HTML

```html
<a onclick="javascript:page(1)" class="page-link">...</a>
```

---

## 2. 페이지 번호 반복

### `start` ~ `end`까지 숫자 나열

```html
<li class="page-item"
    th:each="page : ${#numbers.sequence(start, end)}"
    ...>
```

| 예 | 결과 |
|----|------|
| `start=1`, `end=5` | `page`가 1, 2, 3, 4, 5로 반복 → 화면에 `1 2 3 4 5` |

`#numbers.sequence(a, b)`는 Thymeleaf에서 **a부터 b까지 정수 시퀀스**를 만듭니다.

### 현재 페이지 강조(`active`)

```html
th:classappend="${items.number eq (page - 1)} ? 'active' : ''"
```

| 목적 | 현재 보고 있는 번호에만 `active` 클래스 부여 |
|------|-----------------------------------------------|

**왜 `(page - 1)` 인가?**

| 구분 | 시작 값 |
|------|---------|
| 화면의 `page` | **1부터** (`#numbers.sequence`로 1, 2, 3, …) |
| `items.number` | **0부터** (Spring `Page#getNumber()`) |

그래서 비교할 때는 **같은 기준(0 기반)** 으로 맞춥니다.

- `page == 3`(화면 3페이지)일 때 `items.number`는 `2`  
- 식: `items.number eq (page - 1)` → `2 eq (3 - 1)` → **참** → `active`

**괄호를 꼭 쓰는 이유 (SpEL / IDE 경고)**

- `page-1`처럼 붙여 쓰면, 표현식이 **빼기가 아니라 이상한 토큰**으로 해석될 수 있어 에디터에 **빨간 줄**이 생기거나 동작이 궁금해질 수 있습니다.
- **`(page - 1)`** 또는 **`${page - 1}`** 처럼 **빼기는 공백·괄호로 분명히** 쓰는 것이 안전합니다.

### 번호 클릭 시 이동

```html
<a th:onclick="'javascript:page(' + ${page - 1} + ')'" ...>[[${page}]]</a>
```

| 예 | 계산 | 의미 |
|----|------|------|
| `page = 3` | `3 - 1 = 2` | `page(2)` 호출 → **3페이지**(0 기반 인덱스 2) 요청 |

### 화면에 보이는 숫자

`[[${page}]]` → 사용자에게는 **1, 2, 3, …** 처럼 **1부터** 보입니다.

---

## 3. 다음(Next) 버튼

### 비활성: 마지막 페이지일 때

```html
<li class="page-item" th:classappend="${items.last}?'disabled'">
```

| `items.last` | 의미 |
|--------------|------|
| `true` | 지금이 **마지막 페이지** |
| `false` | 아직 다음 페이지 있음 |

### 클릭: 다음 페이지(인덱스)로 이동

```html
<a th:onclick="'javascript:page(' + ${items.number + 1} + ')'" ...>
```

**예: 지금이 2페이지** → `items.number = 1` → `1 + 1 = 2` → `javascript:page(2)` → **3페이지**로 이동.

`+ 1` 앞뒤로 공백을 두면 `+1`과 붙어 읽히는 문제를 줄일 수 있습니다.

---

## 4. 페이지 번호 그룹 — 빠른 참조

**상세 설명·예제·표**는 위 **`## 페이지 번호 그룹 계산 — 무엇을 위한 코드인가`** 와 **`## th:with` 블록 상세** 에 모두 통합해 두었습니다.

| 키워드 | 한 줄 |
|--------|--------|
| `start` | `(items.number / maxPage) * maxPage + 1` → 그룹의 **첫 번호(1 기반)** |
| `end` | `totalPages==0`이면 `1`, 아니면 `start+(maxPage-1)`과 `totalPages` 중 **전체를 넘지 않게** 선택 |
| `items.number` | **0 기반** 현재 페이지 인덱스 |
| `maxPage` | 한 묶음에 보여 줄 **링크 개수** |

---

## 5. 전체 흐름 그림

```text
[Previous]  →  page(items.number - 1)
[1][2][3]…  →  page(선택한 page - 1), 표시는 [[page]]
[Next]      →  page(items.number + 1)
```

**현재가 3페이지일 때** (`items.number = 2`, 그룹이 1~5라 가정):

```text
Previous  1  2  [3]  4  5  Next
```

- `3`에만 `active`.
- Previous·Next는 각각 2페이지·4페이지로 연결(끝/처음이면 `disabled`).

---

## 6. Bootstrap 클래스 정리

| 클래스 | 역할 |
|--------|------|
| `page-item` | 페이지네이션의 한 항목(`<li>`) |
| `page-link` | 클릭 영역 스타일(`<a>`) |
| `active` | 현재 페이지 강조 |
| `disabled` | 이전/다음 사용 불가 표시 |

---

## 7. 이 코드에서 꼭 기억할 점

1. **Spring `Pageable` / `Page`의 페이지 번호는 0부터** — URL이나 `page(n)`에 넘기는 값은 보통 **0 기반 인덱스**입니다.  
2. **화면에 그리는 번호는 1부터** — `#numbers.sequence(start, end)`와 `[[${page}]]`가 담당합니다.  
3. 그래서 **항상 맞추는 연산**이 들어갑니다.  
   - 화면 `page` → 서버 인덱스: **`page - 1`** (괄호: **`(page - 1)`**)  
   - 이전/다음 한 칸: **`items.number - 1`**, **`items.number + 1`**  
4. **여러 페이지를 한 줄에만 보이게** 하려면 **`start` / `end`** 로 그룹을 자릅니다.

---

## 8. 참고 (실무)

- 나눗셈 `items.number / maxPage`가 **실수 나눗셈**으로 바뀌면 그룹이 깨질 수 있습니다. 정수 나눗션이 되도록 리터럴·타입을 맞추거나, **서버에서 `start`/`end`만 계산해 모델에 넣는** 방식도 많이 씁니다.  
- `totalPages == 0`일 때 `end = 1`은 템플릿이 깨지지 않게 하는 **방어**에 가깝습니다. 데이터 없을 때는 `th:if`로 페이징 블록 전체를 숨길 수도 있습니다.
