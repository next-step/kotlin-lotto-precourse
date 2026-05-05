package lotto.model

data class LottoNumber(val value: Int) {
    init {
        require(value in MIN..MAX) {
            "[ERROR] 로또 번호는 ${MIN}부터 ${MAX} 사이의 숫자여야 합니다."
        }
    }

    companion object {
        const val MIN = 1
        const val MAX = 45
    }
}
