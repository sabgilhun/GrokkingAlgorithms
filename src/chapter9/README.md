# Chapter 9 동적 프로그래밍
문제를 여러개의 하위 문제로 쪼개고, 이 하위 문제들을 먼저 해결하고 그 하위 문제로 전체 문제의 해답을 찾는 방식의 알고리즘

## 배낭 채우기 문제
이전 탐욕 알고리즘으로 풀었던 배낭 채우기 문제를 동적 프로그래밍으로 풀어보자. 조건은 아래와 같다.
> 배낭의 최대 수용 무게: 40kg   
> 담을 수 있는 물건들: 40kg(가격: 300원)의 스테레오, 30kg(가격: 200원)의 노트북, 10kg(가격: 150원)의 기타

위의 조건일때 배낭에 담을 수 있는 최대 가치를 담는 방법을 찾아보자.

### 단순한 방법
제일 단순한 방법은 모든 경우의 수에 대한 가격들을 확인하고 그 중 제일 비싼 조합을 선택하는 것이다.
물건이 3개이고 각 물건을 `넣는 경우`, `넣지 않는 경우`로 나누어 생각하면 총 8가지의 경우가 나온다. (2^3)
* 물건 없음 -> 0
* 기타 -> 150
* 스테레오 -> 300
* 노트북 -> 200
* 기타 + 스테레오 -> 0 (배낭의 수용량을 넘음)
* 기타 + 노트북 -> 350
* 스테레오 + 노트북 -> 0 (배낭의 수용량을 넘음)
* 기타 + 스테레오 + 노트북 -> 0 (배낭의 수용량을 넘음)

위의 모든 경우를 확인해보면 `기타 + 노트북`이 최대 가격이 되는 것을 알 수 있다.   
이 방법으로 문제가 해결되긴 하지만 이 방법의 큰 결점이 있다. 그것은 알고리즘 실행 시간이 `O(2^n)`으로 너무 길다는 점이다.

### 동적 프로그래밍
동적 프로그래밍은 위에서의 문제를 해결 할 수 있다. 그 원리는 문제를 하위 문제로 쪼개고, 쪼개진 문제를 계산한 것을 저장항 다음 하위문제를 풀때 이용하는 것이다.
동적 프로그래밍은 아래와 같이 격자로 부터 시작하는데 행은 `선택할 물건`, 열은 `배낭 수용량을 쪼갠 값들`로 표현된다.

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | | | | |
| 스테레오 | | | | |
| 노트북 | | | | |

위의 표를 하나의 기준으로 하나씩 채워나갈 것이다. 그 기준은 아래와 같다.
> `i(1~3)`가 행 `j(1~3)`가 열을 나타낼때,    
> `1 ~ (i - 1)행 1 ~ j열`까지의 최대 값(M1) OR `i행의 물건의 가치 + (i-1)행 (j-남은 공간)열` 값(M2)으로 i, j 칸 값 설정  
> (값이 없거나 불가능한 경우 0으로 처리)

(1, 1) -> `M1 = 0`, `M2 = 150` -> 150

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | | | |
| 스테레오 | | | | |
| 노트북 | | | | |

(1, 2) ... (1, 4)까지 저장된 값이 없으므로 i행의 물건의 가치가 각 셀의 값이 된다.

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | 150 | 150 | 150 |
| 스테레오 | | | | |
| 노트북 | | | | |

스테레오가 40Kg이기 때문에 3열까지 M2의 값을 계산할 수 없다.  
(2, 1) ... (2, 3) -> `M1 = 150`,  `M2 = 0` -> 150

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | 150 | 150 | 150 |
| 스테레오 | 150 | 150 | 150 | |
| 노트북 | | | | |

(2, 4) -> `M1 = 150`, `M2 = 300` -> 300

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | 150 | 150 | 150 |
| 스테레오 | 150 | 150 | 150 | 300 |
| 노트북 | | | | |

노트북이 30Kg이기 때문에 M1 사용   
(3, 1) ... (3, 2) -> `M1 = 150`, `M2 = 0` -> 150

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | 150 | 150 | 150 |
| 스테레오 | 150 | 150 | 150 | 300 |
| 노트북 | 150 | 150 | | |

(3, 3) -> `M1 = 150`, `M2 = 200` -> 200

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | 150 | 150 | 150 |
| 스테레오 | 150 | 150 | 150 | 300 |
| 노트북 | 150 | 150 | 200 | |

(3, 4) -> `M1 = 150`, `M2 = 200 + 150(2, 1)` -> 350

|  | 10 | 20 | 30 | 40 |
| --- | --- | --- | --- |  --- |
| 기타 | 150 | 150 | 150 | 150 |
| 스테레오 | 150 | 150 | 150 | 300 |
| 노트북 | 150 | 150 | 200 | 350 |

마지막 (3, 4)에서 `M2`를 계산하는 경우를 생각해보자. 이 칸의 문제는 이렇게 표현할 수 있다. 
> 노트북을 넣고 남은 무게에 어떤걸 넣는게 최대 가치일까? -> (노트북 가치) + 10Kg에 넣을 수 있는 최대 가치 = ??

`10Kg에 넣을 수 있는 최대 가치`는 이미 계산을 했다. 바로 (2, 1)칸이다. 이때 (1, 1), (2, 1) 중에 왜 (2, 1)인지 궁금할 수 있는데, 
그 이 해답은 (2, 1) 값을 결정할때 이미 (1, 1)을 고려 했기 때문에 (2, 1) 10Kg 공간에 담을 수 있는 최대 값이 되는 것이다.

[Code](DynamicPrograming.kt)

## 서로 의존적인 물건에 대한 동적 프로그래밍

| 여행지 | 소요 시간 | 점수 |
| --- | --- | --- |
| 런던 | 1.5일 | 8 |
| 파리 | 1.5일 | 9 |
| 독일 | 1.5일 | 7 |

위의 표는 아까 배낭 문제와 비슷해 보인다. 하지만 확인해야 할 것이 있다. 실제론 `런던 -> 독일 -> 파리` 로 움직는 것과 `런던 -> 파리 -> 독일` 같은 시간이 걸리지 않는다.
이런 경우 각 항목이 의존적인 성격을 띈다고 말하는데 이런 경우 동적 프로그래밍을 적용 할 수 없다. `동적 프로그래밍은 하위 문제의 정답이 다른 조건에 영향없이 항성 옳아야 하기 때문이다.`