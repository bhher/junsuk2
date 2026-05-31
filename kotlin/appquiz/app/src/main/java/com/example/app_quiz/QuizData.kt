package com.example.app_quiz

//데이터 모델 - 데이터 저장 전용 클래스
data class QuizQuestion(
    val question: String, //문제 제목(질문) - 코틀린 개발회사?
    val choices: List<String>, //객관식 보기 목록
    val correctIndex: Int, //정답 번호 (index)
)
//choices = listOf(
//"Google",
//"JetBrains",
//"Apple",
//"Microsoft"
//)

val kotlinQuizQuestions = listOf(
    QuizQuestion(
        question = "읽기 전용 변수를 선언할 때 쓰는 키워드는?",
        choices = listOf("var", "val", "const", "let"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "null을 허용하는 타입 표기는?",
        choices = listOf("String!", "String?", "?String", "nullable String"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "리스트를 불변으로 만드는 함수는?",
        choices = listOf("mutableListOf", "listOf", "arrayListOf", "ArrayList"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "when은 주로 어떤 Java 문법을 대체하나요?",
        choices = listOf("for", "switch", "try", "interface"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "data class가 자동 생성해 주지 않는 것은?",
        choices = listOf("copy()", "equals()", "main()", "componentN()"),
        correctIndex = 2,
    ),
)



