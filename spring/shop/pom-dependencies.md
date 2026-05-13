# Shop 프로젝트 — Maven 의존성 및 빌드 플러그인 설명

이 문서는 `pom.xml`에 선언된 **Spring Boot 부모 POM**, **의존성(dependencies)**, **빌드 플러그인(build plugins)** 을 정리하고, 각 항목이 프로젝트에서 어떤 역할을 하는지 설명합니다. **§2.0·§3.0**에는 `pom.xml`에 넣은 **XML 원문(인용)** 이 있습니다. 또한 **QueryDSL을 쓰는 리포지토리 코드**(`ItemRepository` 계열)를 요약해 두었습니다.

---

## 1. 프로젝트 기본 정보

| 항목 | 값 |
|------|-----|
| Parent | `spring-boot-starter-parent` **4.0.6** |
| GroupId / ArtifactId | `com.shop` / `shop` |
| Java 버전 | **17** (`<java.version>17</java.version>`) |

### 1.1 `pom.xml` — 부모 POM·좌표·Java 속성 코드

```5:31:pom.xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.0.6</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.shop</groupId>
	<artifactId>shop</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name/>
	<description/>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
```

부모 POM을 사용하면 Spring Boot가 권장하는 의존성 버전 관리(BOM)와 기본 플러그인 설정을 상속받습니다. 개별 `dependency`에는 버전을 생략한 항목이 많으며, 이는 부모가 관리하는 버전을 따릅니다.

---

## 2. 의존성(Dependencies) 설명

### 2.0 `pom.xml`에 선언한 `<dependencies>` 코드

프로젝트 루트 `pom.xml`의 **의존성 블록 원문**은 다음과 같습니다. Spring Boot 스타터는 부모 POM이 버전을 고정하므로 `<version>`이 없습니다. `thymeleaf-layout-dialect`, `modelmapper`, QueryDSL 계열만 버전(또는 classifier)을 명시합니다.

```32:122:pom.xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-h2console</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>nz.net.ultraq.thymeleaf</groupId>
			<artifactId>thymeleaf-layout-dialect</artifactId>
			<version>3.2.1</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.1.0</version>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-jpa</artifactId>
			<version>5.0.0</version>
			<classifier>jakarta</classifier>
		</dependency>

		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-apt</artifactId>
			<version>5.0.0</version>
			<classifier>jakarta</classifier>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-core</artifactId>
			<version>5.0.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webmvc-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

### 2.1 Spring Boot — 웹·데이터·화면

| Artifact | 설명 |
|------------|------|
| `spring-boot-h2console` | 개발 시 H2 인메모리 DB를 쓸 때, 브라우저에서 `/h2-console` 등으로 접속해 쿼리·스키마를 확인할 수 있게 하는 기능을 제공합니다. |
| `spring-boot-starter-data-jpa` | JPA(Hibernate 등)와 Spring Data JPA를 포함합니다. 엔티티 매핑·리포지토리·트랜잭션 연동에 사용합니다. |
| `spring-boot-starter-thymeleaf` | Thymeleaf 템플릿 엔진과 Spring MVC 연동을 제공합니다. 서버 사이드 HTML 렌더링에 사용합니다. |
| `spring-boot-starter-webmvc` | Spring MVC, Jackson(JSON), 내장 톰캣 등 웹 애플리케이션 구동에 필요한 스타터입니다. |
| `spring-boot-starter-validation` | Bean Validation(`@Valid`, `@NotNull` 등)을 MVC·JPA와 함께 사용할 수 있게 합니다. |

### 2.2 Thymeleaf 확장

| Artifact | 버전 | 설명 |
|----------|------|------|
| `thymeleaf-layout-dialect` | 3.2.1 | 공통 레이아웃(헤더·푸터·네비)을 `layout:decorate` 등으로 분리해 재사용할 때 쓰는 dialect입니다. |

### 2.3 객체 매핑

| Artifact | 버전 | 설명 |
|----------|------|------|
| `modelmapper` | 3.1.0 | DTO ↔ 엔티티 등 서로 다른 타입 간 필드 매핑을 선언적으로 줄여 줍니다. 수동 setter/getter 반복을 줄이는 용도입니다. |

### 2.4 데이터베이스 드라이버

| Artifact | Scope | 설명 |
|----------|--------|------|
| `h2` | `runtime` | H2 JDBC 드라이버. 로컬·테스트에서 인메모리 또는 파일 DB로 빠르게 개발할 때 사용합니다. |
| `mysql-connector-j` | `runtime` | MySQL 8 계열 공식 JDBC 드라이버. 운영 또는 MySQL 프로파일에서 연결할 때 사용합니다. |

`runtime` 스코프는 **컴파일 시 클래스패스에 포함되지 않고**, 실행·패키징 시에만 포함됩니다. DB API는 JPA 추상화로 쓰는 경우가 많아 컴파일에 직접 드라이버가 필요하지 않을 수 있습니다(프로젝트 설정에 따라 다름).

### 2.5 생산성·보안

| Artifact | 설명 |
|----------|------|
| `lombok` (`optional`) | `@Getter`, `@Builder`, `@Slf4j` 등으로 보일러플레이트 코드를 줄입니다. `optional`이라 이 라이브러리를 의존하는 다른 모듈로 전파되지 않게 할 수 있습니다. |
| `spring-boot-starter-security` | 인증·인가, CSRF, 세션/폼 로그인 등 보안 기본 설정을 제공합니다. |

### 2.6 QueryDSL (타입 안전 쿼리)

| Artifact | 버전 / 분류 | Scope | 설명 |
|----------|----------------|--------|------|
| `querydsl-jpa` | 5.0.0, classifier `jakarta` | (기본) | JPA 엔티티 기준으로 `JPAQueryFactory` 등 타입 안전 쿼리 API를 사용합니다. Jakarta EE 네임스페이스용 아티팩트입니다. |
| `querydsl-apt` | 5.0.0, `jakarta` | `provided` | 컴파일 시 **Q클래스**(예: `QItem`)를 생성하는 어노테이션 프로세서입니다. 런타임 JAR에는 넣지 않는 것이 일반적입니다. |
| `querydsl-core` | 5.0.0 | (기본) | QueryDSL 핵심 표현식·빌더. `querydsl-jpa`와 함께 쓰입니다. |

커스텀 리포지토리 구현(`ItemRepositoryCustomImpl` 등)에서 동적 쿼리를 짤 때 이 스택을 사용합니다.

### 2.7 테스트 전용(`test` scope)

| Artifact | 설명 |
|----------|------|
| `spring-boot-starter-security-test` | `@WithMockUser`, `MockMvc`와 Security 연동 등 보안 관련 테스트 지원. |
| `spring-boot-starter-data-jpa-test` | `@DataJpaTest` 등 JPA 슬라이스 테스트. |
| `spring-boot-starter-thymeleaf-test` | Thymeleaf 관련 테스트 유틸. |
| `spring-boot-starter-webmvc-test` | `MockMvc`, `@WebMvcTest` 등 웹 레이어 테스트. |

`test` 스코프는 **테스트 소스 컴파일·실행 시에만** 클래스패스에 올라가며, 배포 산출물에는 보통 포함되지 않습니다.

---

## 3. 빌드 플러그인(Build Plugins) 설명

### 3.0 `pom.xml`에 선언한 `<build><plugins>` 코드

**Spring Boot 패키징 플러그인**과 **`maven-compiler-plugin`** 의 어노테이션 프로세서 경로를 한 블록으로 넣은 내용입니다.

```124:198:pom.xml
	<build>
		<plugins>

			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<executions>
					<execution>
						<id>default-compile</id>
						<phase>compile</phase>
						<goals>
							<goal>compile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
								<path>
									<groupId>com.querydsl</groupId>
									<artifactId>querydsl-apt</artifactId>
									<version>5.0.0</version>
									<classifier>jakarta</classifier>
								</path>
								<path>
									<groupId>jakarta.persistence</groupId>
									<artifactId>jakarta.persistence-api</artifactId>
									<version>3.2.0</version>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
					<execution>
						<id>default-testCompile</id>
						<phase>test-compile</phase>
						<goals>
							<goal>testCompile</goal>
						</goals>
						<configuration>
							<annotationProcessorPaths>
								<path>
									<groupId>org.projectlombok</groupId>
									<artifactId>lombok</artifactId>
								</path>
								<path>
									<groupId>com.querydsl</groupId>
									<artifactId>querydsl-apt</artifactId>
									<version>5.0.0</version>
									<classifier>jakarta</classifier>
								</path>
								<path>
									<groupId>jakarta.persistence</groupId>
									<artifactId>jakarta.persistence-api</artifactId>
									<version>3.2.0</version>
								</path>
							</annotationProcessorPaths>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
```

### 3.1 `spring-boot-maven-plugin`

- **역할**: 실행 가능한 fat JAR(또는 WAR) 패키징, `spring-boot:run` 등 Spring Boot 전용 Maven 목표를 제공합니다.
- **이 프로젝트의 설정**: `excludes`로 **Lombok**을 최종 패키지에서 제외합니다.
  - Lombok은 **컴파일 타임**에만 필요하고, 바이트코드에는 이미 getter 등이 반영되므로 실행 JAR에 넣지 않아도 됩니다.
  - JAR 크기 감소와 의존성 혼동 방지에 도움이 됩니다.

### 3.2 `maven-compiler-plugin` + `annotationProcessorPaths`

- **역할**: Java 소스를 바이트코드로 컴파일합니다. 여기서는 **어노테이션 프로세서 경로**를 명시해 Lombok과 QueryDSL Q타입 생성이 안정적으로 동작하도록 했습니다.
- **실행(execution) 두 가지**:
  1. **`default-compile`** (`compile` 페이즈): 메인 소스(`src/main/java`) 컴파일 시 APT 적용.
  2. **`default-testCompile`** (`test-compile` 페이즈): 테스트 소스(`src/test/java`) 컴파일 시에도 동일한 APT 적용.

**등록된 annotation processor 경로**

| 경로 | 설명 |
|------|------|
| `lombok` | Lombok 어노테이션 처리. |
| `querydsl-apt` (5.0.0, `jakarta`) | 엔티티를 스캔해 `Q*` 클래스 생성. |
| `jakarta.persistence-api` (3.2.0) | QueryDSL APT가 JPA 메타모델을 해석할 때 필요한 API를 클래스패스에 둡니다. |

IDE에서도 동일한 APT 설정을 맞추면, Eclipse/IntelliJ에서 Q클래스·Lombok이 일관되게 인식됩니다.

---

## 4. QueryDSL 적용 코드 (프로젝트 소스)

`pom.xml`의 QueryDSL 의존성·APT 설정은 아래 **리포지토리 커스텀 구현**에서 실제로 사용됩니다.

### 4.1 Spring Data JPA 커스텀 리포지토리 규칙

- 인터페이스: `ItemRepository`가 상속하는 **`ItemRepositoryCustom`** 이름에 `Custom` 접미사.
- 구현체: 같은 패키지에 **`ItemRepositoryCustomImpl`** 클래스로 두면, Spring Data JPA가 구현체를 자동으로 묶어 줍니다.
- 이 패턴으로 `JpaRepository`의 기본 CRUD와 별도로 **동적 JPQL 대신 QueryDSL**로 복잡한 조회를 추가할 수 있습니다.

### 4.2 `ItemRepository` — 기본 JPA + Predicate 실행기 + 커스텀

```1:9:src/main/java/com/shop/repository/ItemRepository.java
package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Long> ,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
}
```

| 상속 | 용도 |
|------|------|
| `JpaRepository<Item, Long>` | `save`, `findById`, `delete` 등 기본 CRUD |
| `QuerydslPredicateExecutor<Item>` | `Predicate`를 넘기는 QueryDSL 스타일 단순 조회 API 사용 가능 |
| `ItemRepositoryCustom` | 이 프로젝트에서 정의한 **`getAdminItemPage`** 등 커스텀 메서드 |

### 4.3 `ItemRepositoryCustom` — 커스텀 메서드 선언

관리자 상품 목록에서 쓸 검색 DTO와 `Pageable`을 받아, 조건에 맞는 `Item`을 **페이지 단위**로 돌려줍니다.

```10:16:src/main/java/com/shop/repository/ItemRepositoryCustom.java
public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
```

### 4.4 `ItemRepositoryCustomImpl` — QueryDSL 동적 쿼리·페이징

**`JPAQueryFactory`**: 생성자에서 `EntityManager`를 받아 `new JPAQueryFactory(em)`으로 만듭니다. 별도 `@Configuration` 빈 등록 없이 구현 클래스 안에서 쿼리 팩토리를 구성하는 방식입니다.

**`QItem`**: `Item` 엔티티에 대해 APT(`querydsl-apt`, `jakarta`)가 생성하는 메타 클래스입니다. `QItem.item`으로 컬럼·연관을 타입 안전하게 참조합니다.

**`BooleanExpression`**: 조건을 메서드로 나누고, 인자가 `null`이거나 의미 없는 값이면 `null`을 반환해 `.where(...)`에서 **해당 조건이 빠지도록** 하여 동적 쿼리를 구성합니다.

**페이징**: `offset` / `limit`으로 본문 리스트를 조회한 뒤, 같은 `where`로 `Wildcard.count` 집계를 한 번 더 실행해 `total`을 구하고 `PageImpl`로 감쌉니다.

| private 메서드 | 역할 |
|----------------|------|
| `searchSellStatusEq` | `ItemSellStatus` 일치 조건. `searchSellStatus == null`이면 조건 없음. |
| `regDtsAfter` | `searchDateType`(`all`, `1d`, `1w`, `1m`, `6m`)에 따라 기준 시각을 정하고 `regTime.after(...)` 조건. |
| `searchByLike` | `searchBy`가 `itemNm`이면 상품명, `createdBy`면 등록자 필드에 `LIKE %검색어%`. |

핵심 조회 부분:

```65:88:src/main/java/com/shop/repository/ItemRepositoryCustomImpl.java
    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        List<Item> content = queryFactory
                .selectFrom(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()), //등록날짜 조건
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()), // 판매 상태 조건
                        searchByLike(itemSearchDto.getSearchBy(), //검색어 조건 (상품명 또는 등록자)
                                itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc()) //id 역순으로 정렬
                .offset(pageable.getOffset()) //데이타를 가져올 시작위치 (2페이지면 10번부터)
                .limit(pageable.getPageSize()) //한페이지에 보여줄 데이터 개수 (예 : 10개)
                .fetch();//쿼리를 실행 ! 결과를리스트로 반환함

//페이징 내비게이션 [이전][1][2][3][다음] 만들려면 total 값이 있어야 가능
        long total = queryFactory.select(Wildcard.count).from(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne() //count 쿼리는 결과 가 항상 숫자 하나
                ;
        //select count(*)
        return  new PageImpl<>(content, pageable, total);
    }
```

사용 중인 주요 import는 다음과 같습니다.

- `com.querydsl.jpa.impl.JPAQueryFactory`
- `com.querydsl.core.types.dsl.BooleanExpression`, `Wildcard`
- `com.shop.entity.QItem`
- `org.springframework.data.domain.Page`, `PageImpl`, `Pageable`

### 4.5 엔티티와 생성 클래스

- **엔티티**: `com.shop.entity.Item` — `@Entity`, `@Table(name = "item")` 등으로 JPA 매핑.
- **Q타입**: 컴파일 후 **`QItem`** 이 생성되며, 소스 트리에는 없을 수 있습니다. Maven은 보통 `target/generated-sources/annotations` 등에 두고, IDE에서는 해당 경로를 generated source로 지정합니다.

---

## 5. 한눈에 보는 구조

```
pom.xml
├── parent: Spring Boot 4.0.6
├── properties: Java 17
├── dependencies
│   ├── 웹·JPA·Thymeleaf·Validation·Security
│   ├── thymeleaf-layout-dialect, modelmapper
│   ├── H2, MySQL (runtime)
│   ├── lombok, QueryDSL (jpa / apt / core)
│   └── 테스트 스타터들 (test)
└── build/plugins
    ├── spring-boot-maven-plugin (Lombok exclude)
    └── maven-compiler-plugin (Lombok + QueryDSL APT + JPA API)

QueryDSL 사용 코드 (상품 관리자 목록)
└── src/main/java/com/shop/repository/
    ├── ItemRepository.java          ← Custom + QuerydslPredicateExecutor
    ├── ItemRepositoryCustom.java    ← 메서드 선언
    └── ItemRepositoryCustomImpl.java ← JPAQueryFactory, QItem, 동적 where, PageImpl
```

---

## 6. 참고

- 버전·아티팩트 이름은 **`pom.xml`이 단일 진실 소스**입니다. 이 문서와 차이가 있으면 `pom.xml`을 기준으로 하세요.
- QueryDSL Q클래스 출력 디렉터리는 `pom.xml`의 `build-helper-maven-plugin` 등 추가 설정이 없다면, 기본적으로 `target/generated-sources/java` 또는 `target/generated-sources/annotations` 아래에 생성되는 경우가 많습니다. IDE에서 해당 폴더를 generated source로 표시해야 할 수 있습니다.
