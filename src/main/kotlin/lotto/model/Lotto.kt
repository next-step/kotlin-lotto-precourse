package lotto.model

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == SIZE) {
            "[ERROR] 로또는 ${SIZE}개의 번호로 구성되어야 합니다."
        }
        require(numbers.toSet().size == SIZE) {
            "[ERROR] 로또 번호는 중복될 수 없습니다."
        }
    }

    companion object {
        const val SIZE = 6
    }
}
