package com.example.app_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_quiz.ui.theme.AppquizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                    MaterialTheme{
                        QuizApp()

            }
        }
    }
}

@Composable
fun QuizApp(viewModel: QuizViewModel = viewModel()){ //QuizViewModel 생성 , 유지
    val state by viewModel.uiState.collectAsState() // StatFlow -> Compose 상태로 변환
    //  StatFlow(보관함 - 현재 좋아요갯수 , 유저이름) 값이 바뀔때마다 신호를 보냄
    // Compose(화면 기억장치) - state 타입이 변경될때 화면 새로 을 새로고침
//collectAsState()  - 중간 번역기
   //viewModel 에 있는   StatFlow 값이 바뀌어도   Compose  ui 바뀌지 않음
    // collectAsState() 통역사 StatFlow 값이 바뀌면  Compose 들을 수 있는 언어(State) 
    //실시간 통역 즉시 ui 새로고침
    Scaffold { innerPadding -> //기본화면 + 시스템 바 패딩
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when {
                state.isFinished -> ResultScreen( //결과 - 점수 , 다시풀기
                    score = state.score,
                    total = state.totalQuestions,
                    onRestart = viewModel::restart,
                )
                state.currentQuestion != null -> QuestionScreen( //문제(보기/다음)
                    index = state.currentIndex + 1,
                    total = state.totalQuestions,
                    question = state.currentQuestion!!,
                    feedback = state.lastAnswerCorrect,
                    onAnswer = viewModel::submitAnswer,
                    onNext = viewModel::nextQuestion,
                    canGoNext = state.lastAnswerCorrect != null,
                )
            }
        }
    }
}
@Composable
fun QuestionScreen(
    index: Int, //현재 문제 번호(1~5)
    total: Int, //전체 문제 수
    question: QuizQuestion, // 문제 + 보기 + 정답 index
    feedback: Boolean?, //null 미선택 , true/false = 정오답
    onAnswer: (Int) -> Unit, // 보기 클릭시 (viewMoel.submitAnswer)
    onNext: () -> Unit, //다음버튼(viewMoel.nextQuestion)
    canGoNext: Boolean,// 다음 버튼 할성 여부
){
    Text("문제 $index / $total" , style= MaterialTheme.typography.labelLarge) //문제 1/5
    Text(question.question, style = MaterialTheme.typography.titleLarge) //문제 문장(QuizData.kt)
    //현재문제 데이터  앞에 question - question:QuizQuestion (데이터 전체) 뒤에 question  val question :String
    question.choices.forEachIndexed { i, choice -> //forEachIndexed  보기마다 버튼하나
        OutlinedButton(
            onClick = { onAnswer(i) }, // 0,1,2,3 -> submitAnswer(2) 실행
            modifier = Modifier.fillMaxWidth(),//버튼이 가로를 꽉채움
            enabled = feedback == null, // 답고르기 전만 클릭가능 
        ) {
            Text(choice)
        }
    }
    feedback?.let { correct -> // 정 오답 피드백
        Card(modifier = Modifier.fillMaxWidth()) { //null 카드 안보임
            Text(
                text = if (correct) "정답입니다!" else "오답입니다.",
                modifier = Modifier.padding(16.dp),
                color = if (correct) MaterialTheme.colorScheme.primary // 정답입니다. (primary)
                else MaterialTheme.colorScheme.error,
            )
        }
    }

    Button( // 답 안 고름 - 비활성화   답고름 + 마지막아님 "다음문제" 답고름 + 마지막 문제 =  결과보기

        onClick = onNext,
        enabled = canGoNext,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(if (index == total) "결과 보기" else "다음 문제")
    }

}
//QuizApp state.isFinished == true 일때만 표시
@Composable
fun ResultScreen(score : Int , total: Int, onRestart:() -> Unit){
    Text("퀴즈 완료!", style = MaterialTheme.typography.headlineMedium)
    Text(
        "점수: $score / $total",  // 점수 3/5
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(vertical = 24.dp),
    )
    Text(
        when {
            score == total -> "만점! Kotlin 기초를 잘 이해하고 있어요."
            score >= total / 2 -> "좋아요. 틀린 문제를 다시 복습해 보세요."
            else -> "가이드 문서를 보며 다시 도전해 보세요."
        },
    )
    Button(onClick = onRestart, modifier = Modifier.fillMaxWidth()) {
        Text("다시 풀기")
    }
}

