# kotlin-lotto-precourse

우아한테크코스 프리코스 - 로또 미션 구현

## 구현할 기능 목록

### 입력 (InputView)
- [x] 구입 금액 입력 받기
- [x] 당첨 번호 입력 받기 (쉼표 구분)
- [x] 보너스 번호 입력 받기

### 도메인 (Model)
- [x] `PurchaseAmount`: 구입 금액 검증 (1,000원 단위, 양수)
- [x] `Lotto`: 로또 1장 (6개 번호, 1~45 범위, 중복 불가)
- [x] `BonusNumber`: 보너스 번호 (1~45, 당첨 번호와 중복 불가)
- [x] `LottoMachine`: 구입 금액만큼 로또 발행
- [x] `Rank`: 등수와 상금 (1~5등, NONE)
- [x] `WinningResult`: 당첨 통계 집계 및 수익률 계산

### 출력 (OutputView)
- [x] 구매한 로또 개수 및 번호 출력
- [x] 당첨 통계 출력
- [x] 총 수익률 출력 (소수점 첫째 자리)

### 예외 처리
- [x] 잘못된 입력시 `IllegalArgumentException` 발생
- [x] `[ERROR]`로 시작하는 메시지 출력
- [x] 해당 입력 단계부터 다시 입력받기

## 패키지 구조

```
src/main/kotlin
├─ Application.kt
└─ lotto
   ├─ controller/LottoController.kt
   ├─ model/
   │  ├─ BonusNumber.kt
   │  ├─ Lotto.kt
   │  ├─ LottoConstants.kt
   │  ├─ LottoMachine.kt
   │  ├─ PurchaseAmount.kt
   │  ├─ Rank.kt
   │  └─ WinningResult.kt
   ├─ util/
   │  ├─ LottoNumberGenerator.kt
   │  └─ RandomLottoNumberGenerator.kt
   └─ view/
      ├─ InputView.kt
      └─ OutputView.kt
```

## 실행 방법

```bash
./gradlew clean test    # 테스트 실행
./gradlew run           # 애플리케이션 실행 (필요시 application 플러그인 추가)
```

또는 IntelliJ에서 `Application.kt`의 `main` 함수를 실행한다.

## 프로그래밍 요구 사항 준수

- Kotlin Coding Conventions 준수
- 들여쓰기 깊이 2 이하
- 함수 길이 15줄 이하
- 함수는 한 가지 일만 수행
- `else` 예약어 미사용 (early return / when 활용)
- JUnit 5 + AssertJ로 테스트 작성

## AI 도구 활용 기록

초안 골격(MVC 분리, Rank enum, WinningResult 집계 로직)은 AI 도구의 도움을 받아 작성하였다.
이후 다음과 같이 직접 분석/수정하였다:
- `Rank.of()`에서 5개 일치-보너스/일반을 분기하는 로직을 단일 진입점으로 정리
- 입력 단계에서 예외가 발생해도 해당 단계부터 다시 입력받도록 `LottoController.retryOnError`를 도입
- `value class`로 `PurchaseAmount`, `BonusNumber`를 도입해 원시값 검증을 캡슐화
- 모든 단위 테스트를 직접 작성하여 도메인 규칙을 검증

## 회고

이번 미션은 생각만큼 쉽지 않았습니다.
처음에는 AI를 사용해 초안을 만들었지만 그대로 쓰지 않고 코드를 분석하며 이해하려고 노력하였습니다.
그 과정에서 함수 분리와 테스트 작성의 필요성을 더 잘 알게 되었습니다.
기능을 분리해 기능 단위로 커밋하는 것이 쉽지 않았지만, 연습을 통해 점차 익숙해졌습니다.
결국 더 명확하게 구현할 수 있었고, 미션을 수행하면서 성장한 걸 느꼈습니다.
