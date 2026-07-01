# React 프로젝트 Vercel 배포 가이드

React 프로젝트를 Vercel에 배포하는 전체 과정을 **가입 → GitHub 연결 → React 업로드 → 배포** 순서로 정리했습니다.

---

## 목차

1. [사전 준비](#1-사전-준비)
2. [Vercel 가입](#2-vercel-가입)
3. [GitHub 연결](#3-github-연결)
4. [React 프로젝트 GitHub 업로드](#4-react-프로젝트-github-업로드)
5. [Vercel 배포](#5-vercel-배포)
6. [배포 확인 및 수정](#6-배포-확인-및-수정)
7. [자주 발생하는 문제](#7-자주-발생하는-문제)

---

## 1. 사전 준비

배포 전에 아래 항목이 준비되어 있어야 합니다.

| 항목 | 설명 |
|------|------|
| React 프로젝트 | 로컬에서 정상 실행되는 프로젝트 |
| GitHub 계정 | 코드 저장소용 |
| Git 설치 | [https://git-scm.com](https://git-scm.com) |

### 로컬에서 빌드 테스트

배포 전에 프로젝트가 정상 빌드되는지 확인합니다.

```bash
# 프로젝트 폴더로 이동
cd my-react-app

# 의존성 설치
npm install

# 개발 서버 실행 (선택)
npm run dev

# 빌드 테스트 (필수)
npm run build
```

> **Vite 프로젝트:** `npm run dev`, `npm run build`  
> **Create React App:** `npm start`, `npm run build`

빌드가 성공하면 `dist` 또는 `build` 폴더가 생성됩니다.

---

## 2. Vercel 가입

### 2-1. Vercel 사이트 접속

1. [https://vercel.com](https://vercel.com) 접속
2. 우측 상단 **Sign Up** 클릭

### 2-2. 가입 방법 선택

가장 쉬운 방법은 **GitHub 계정으로 가입**하는 것입니다.

1. **Continue with GitHub** 선택
2. GitHub 로그인
3. Vercel이 GitHub 저장소에 접근할 수 있도록 권한 허용

> GitHub로 가입하면 이후 저장소 연결이 훨씬 간단합니다.

### 2-3. 가입 완료

가입이 끝나면 Vercel 대시보드가 표시됩니다.

---

## 3. GitHub 연결

Vercel과 GitHub를 연결하면, GitHub에 코드를 올릴 때마다 자동으로 배포할 수 있습니다.

### 3-1. GitHub 저장소 권한 확인

1. Vercel 대시보드 접속
2. 상단 **Add New...** → **Project** 클릭
3. GitHub 저장소 목록이 보이면 연결 완료

### 3-2. 저장소가 안 보일 때

1. **Adjust GitHub App Permissions** 또는 **Configure GitHub App** 클릭
2. Vercel이 접근할 저장소 선택
   - **All repositories** (전체)
   - **Only select repositories** (특정 저장소만)
3. **Save** 클릭

---

## 4. React 프로젝트 GitHub 업로드

로컬 React 프로젝트를 GitHub 저장소에 올립니다.

### 4-1. GitHub에서 새 저장소 만들기

1. [https://github.com/new](https://github.com/new) 접속
2. Repository name 입력 (예: `my-react-app`)
3. Public 또는 Private 선택
4. **README, .gitignore, license는 추가하지 않음** (로컬 프로젝트가 이미 있으므로)
5. **Create repository** 클릭

### 4-2. 로컬 프로젝트 Git 초기화

터미널에서 React 프로젝트 폴더로 이동 후 실행합니다.

```bash
cd my-react-app

# Git 초기화 (처음 한 번만)
git init

# .gitignore 확인 (node_modules, dist, build 등 제외)
# 없다면 아래 내용으로 생성
```

`.gitignore` 예시:

```
node_modules
dist
build
.env
.env.local
.DS_Store
```

### 4-3. GitHub에 업로드

```bash
# 파일 추가
git add .

# 첫 커밋
git commit -m "Initial commit: React project"

# 기본 브랜치 이름 설정
git branch -M main

# GitHub 저장소 연결 (본인 계정/저장소명으로 변경)
git remote add origin https://github.com/사용자이름/my-react-app.git

# GitHub에 업로드
git push -u origin main
```

업로드가 완료되면 GitHub 저장소 페이지에서 코드를 확인할 수 있습니다.

---

## 5. Vercel 배포

### 5-1. 새 프로젝트 생성

1. [Vercel 대시보드](https://vercel.com/dashboard) 접속
2. **Add New...** → **Project** 클릭
3. GitHub 저장소 목록에서 방금 업로드한 `my-react-app` 선택
4. **Import** 클릭

### 5-2. 프로젝트 설정

Vercel이 React 프로젝트를 자동으로 감지합니다. 대부분 기본값 그대로 사용하면 됩니다.

| 설정 항목 | Vite | Create React App |
|-----------|------|------------------|
| Framework Preset | Vite | Create React App |
| Build Command | `npm run build` | `npm run build` |
| Output Directory | `dist` | `build` |
| Install Command | `npm install` | `npm install` |

> Output Directory를 잘못 설정하면 배포 후 빈 화면이 나올 수 있습니다.

### 5-3. 환경 변수 (필요한 경우)

API 키 등 환경 변수가 있다면 **Environment Variables**에 추가합니다.

```
VITE_API_URL=https://api.example.com
```

> Vite는 `VITE_` 접두사, CRA는 `REACT_APP_` 접두사를 사용합니다.

### 5-4. 배포 시작

1. **Deploy** 버튼 클릭
2. 빌드 로그 확인
3. **Congratulations!** 메시지가 나오면 배포 완료

배포가 끝나면 아래와 같은 주소가 생성됩니다.

```
https://my-react-app.vercel.app
```

---

## 6. 배포 확인 및 수정

### 6-1. 사이트 확인

생성된 URL을 브라우저에서 열어 정상 동작하는지 확인합니다.

### 6-2. 코드 수정 후 재배포

GitHub에 push하면 Vercel이 **자동으로 다시 배포**합니다.

```bash
# 코드 수정 후
git add .
git commit -m "Update: 내용 수정"
git push
```

push 후 Vercel 대시보드에서 새 배포 상태를 확인할 수 있습니다.

### 6-3. 도메인 변경 (선택)

1. Vercel 프로젝트 → **Settings** → **Domains**
2. 원하는 도메인 입력
3. 안내에 따라 DNS 설정

---

## 7. 자주 발생하는 문제

### 빌드 실패

```bash
# 로컬에서 먼저 빌드 테스트
npm run build
```

로컬에서도 실패하면 오류 메시지를 수정한 뒤 다시 push합니다.

### 배포 후 빈 화면

- **Output Directory** 확인
  - Vite: `dist`
  - CRA: `build`

### React Router 사용 시 404 오류

`vercel.json` 파일을 프로젝트 루트에 추가합니다.

```json
{
  "rewrites": [
    { "source": "/(.*)", "destination": "/index.html" }
  ]
}
```

### 환경 변수가 적용되지 않음

- Vercel 대시보드 → **Settings** → **Environment Variables**에서 값 확인
- 변수명 접두사 확인 (`VITE_`, `REACT_APP_`)
- 수정 후 **Redeploy** 실행

---

## 전체 흐름 요약

```
1. Vercel 가입 (GitHub 계정 권장)
        ↓
2. GitHub 연결 (저장소 권한 허용)
        ↓
3. React 프로젝트를 GitHub에 push
        ↓
4. Vercel에서 저장소 Import
        ↓
5. Deploy 클릭 → 배포 완료
        ↓
6. 이후 push할 때마다 자동 재배포
```

---

## 참고 링크

- [Vercel 공식 문서](https://vercel.com/docs)
- [Vite 배포 가이드](https://vitejs.dev/guide/static-deploy.html#vercel)
- [Create React App 배포](https://create-react-app.dev/docs/deployment/)
