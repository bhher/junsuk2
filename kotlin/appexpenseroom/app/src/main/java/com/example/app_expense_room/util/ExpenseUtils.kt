package com.example.app_expense_room.util

import com.example.app_expense_room.model.Expense
import java.util.Calendar

//월 필터 , 검색  , 금액 포맷

//이번 달 항목만 
fun matchesMonth(expense: Expense, year: Int, month: Int) : Boolean {
    val cal = Calendar.getInstance().apply { timeInMillis = expense.createdAt }
    return cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) + 1 == month
}
// 검색 - 제목 , 메모, 카테고리
fun matchesSearch(expense: Expense, query: String): Boolean {
    if (query.isBlank()) return true
    val q = query.trim().lowercase()
    return expense.title.lowercase().contains(q) ||
            expense.memo.lowercase().contains(q) ||
            expense.category.lowercase().contains(q)
}

fun formatAmount(amount: Long): String = "%,d원".format(amount)

fun monthLabel(year: Int, month: Int): String = "${year}년 ${month}월"