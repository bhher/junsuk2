# Tailwind CSS — Vite + React 설정 정리

Vite 기준으로 **Tailwind 3**과 **Tailwind 4**를 나란히 정리했습니다.

---

## Tailwind CSS 3

### 1. 패키지 설치

```bash
npm install -D tailwindcss@3 postcss autoprefixer
```

### 2. 설정 파일 생성

```bash
npx tailwindcss init -p
```

- `tailwind.config.js` — Tailwind 설정
- `postcss.config.js` — PostCSS에서 `tailwindcss`, `autoprefixer` 연결 (`-p` 옵션)

### 3. `tailwind.config.js` 수정

`content`에 HTML·JSX 등 소스 경로를 넣어야 클래스가 빌드에 포함됩니다.

```js
/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {},
  },
  plugins: [],
}
```

(CommonJS를 쓰는 프로젝트면 `module.exports = { ... }` 형태로 동일 내용을 쓰면 됩니다.)

### 4. CSS 진입 파일 (`src/index.css` 등)

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

### 5. 진입 JS에서 CSS import

```js
import './index.css'
```

### 6. Vite와의 관계

- Vite는 기본적으로 PostCSS를 사용하므로 **`postcss.config.js`만 있으면** Tailwind 3가 동작합니다.
- 별도의 `vite.config.js`에 Tailwind 전용 플러그인은 필요 없습니다.

---

## Tailwind CSS 4 (Vite)

v4는 **Vite 전용 플러그인**(`@tailwindcss/vite`)으로 통합하는 방식이 권장됩니다. PostCSS + `tailwind.config.js`만 쓰는 v3 흐름과 다릅니다.

### 1. 패키지 설치

```bash
npm install -D tailwindcss @tailwindcss/vite
```

### 2. `vite.config.js`에 플러그인 추가

`@tailwindcss/vite`를 import하고 **`plugins`에 반드시 등록**해야 합니다. 보통 `tailwindcss()`를 **가장 앞**에 둡니다.

```js
import { defineConfig } from 'vite'
import tailwindcss from '@tailwindcss/vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [
    tailwindcss(),
    react(),
  ],
})
```

### 3. CSS 진입 파일 (`src/index.css`)

v4는 한 줄로 Tailwind를 불러옵니다.

```css
@import "tailwindcss";
```

(`@tailwind base/components/utilities` 대신 위 방식을 사용합니다.)

### 4. 진입 JS에서 CSS import

```js
import './index.css'
```

### 5. v3와의 차이 요약

| 항목 | Tailwind 3 | Tailwind 4 (Vite) |
|------|------------|-------------------|
| 주요 패키지 | `tailwindcss@3`, `postcss`, `autoprefixer` | `tailwindcss`, `@tailwindcss/vite` |
| 설정 생성 | `npx tailwindcss init -p` | 보통 수동으로 `vite.config.js` + `index.css` |
| PostCSS | `postcss.config.js` 사용 | Vite 플러그인 사용 시 **필수는 아님** |
| `tailwind.config.js` | `content` 등 전통적 설정 | 선택·CSS 기반 `@theme` 등 v4 방식도 가능 |
| CSS | `@tailwind base/components/utilities` | `@import "tailwindcss"` |

### 6. 자주 나는 실수

- v4 + Vite에서 **`vite.config.js`의 `plugins`에 `tailwindcss()`를 넣지 않음** → 스타일이 적용되지 않음.
- `index.css`에 v3용 `@tailwind`만 넣고 v4 패키지를 쓰는 경우 → v4 문서에 맞게 `@import "tailwindcss"`로 맞출 것.

---

## 참고

- 공식 문서: [Tailwind CSS](https://tailwindcss.com/docs/installation)
- Vite용 v4: 문서의 “Vite” 가이드와 동일한 흐름입니다.
