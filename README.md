# Intelligent Bank
> 똑똑한 은행, 간편하게 사용가능한 가벼운 인터넷 은행입니다.

# 1. 사용 기술 스택
* Spring Boot 3.0.2
* Java17
* Spring Data Jpa & Query Dsl
* Spring Security & Jwt
* Apache Commons Lang3
* MySql
* LomBok
* Gradle
* Junit5

# 2. 설명
* 비즈니스 종류는 간편 인터넷 은행입니다.
* [프로젝트 위키](https://github.com/liveforone/intelligent_bank/wiki)를 보시면 상세한 프로젝트 설계와 각 도메인별 api문서, DB설계, ERD, 상세 요구사항, 구현 기술 등이 기재되어있습니다.
* 모든 문서는 위키로 제작하였고, 링크를 달아놓았으니 클릭하셔서 보실 수 있습니다.
* 해당 프로젝트는 현재 읽고 계신 README의 4번에 기술되어있는  스타일 가이드를 지켜 제작하였습니다.
* 서버와 DB 부하를 줄이는 스케일러빌리티한 시스템을 만들려고 많이 노력하였습니다.
* 또한 비즈니스 도메인 특성상 트랜잭션이 매우 중요하여 트랜잭션 단위로 분류하여 계층을 설계하려고 노력하였습니다.

# 3. 전체 문서 위키주소
### a. 프로젝트 소개
* [프로젝트 소개](https://github.com/liveforone/intelligent_bank/wiki/%EB%93%A4%EC%96%B4%EA%B0%80%EB%A9%B0)
### b. 아키텍처 설계
* [아키텍처 설계 위키](https://github.com/liveforone/intelligent_bank/wiki/%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%84%A4%EA%B3%84-%EB%B0%8F-%EC%9D%B4%EC%9C%A0)
### c. 도메인별 위키
* [회원시스템](https://github.com/liveforone/intelligent_bank/wiki/%ED%9A%8C%EC%9B%90%EC%8B%9C%EC%8A%A4%ED%85%9C)
* [통장시스템](https://github.com/liveforone/intelligent_bank/wiki/%ED%86%B5%EC%9E%A5-%EC%8B%9C%EC%8A%A4%ED%85%9C)
* [거래내역시스템]()
* [알림시스템]()
* [송금시스템]()
* [ATM시스템]()
* [대출시스템]()
* [통계시스템]()
### d. 데이터 베이스 설계
* [데이터 베이스 설계 위키]()
### e. 고민한 점

# 4. 스타일 가이드
* 스타일 가이드는 필자가 생각하는 좋은 코드와 필자의 클린코드 철학이 담긴 문서이다.
* 해당 프로젝트는 스타일가이드를 모두 지키며 코드를 작성했다.
* [나만의 스타일 가이드](https://github.com/liveforone/study/tree/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D)에서 전문을 읽을 수 있다.
* [가독성](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/b.%20%EA%B0%80%EB%8F%85%EC%84%B1.md)
* [Null과 중복체크](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/c.%20Null%EA%B3%BC%20%EC%A4%91%EB%B3%B5%20%EC%B2%B4%ED%81%AC.md)
* [분기문은 gate-way style로 하라](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/d.%20%EB%B6%84%EA%B8%B0%EB%AC%B8%EC%9D%80%20gate-way%20%EC%8A%A4%ED%83%80%EC%9D%BC%EB%A1%9C%20%ED%95%98%EB%9D%BC.md)
* [Mapper 클래스](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/e.%20Mapper%20%ED%81%B4%EB%9E%98%EC%8A%A4.md)
* [매직넘버를 없애라](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/f.%20%EB%A7%A4%EC%A7%81%EB%84%98%EB%B2%84%EB%A5%BC%20%EC%97%86%EC%95%A0%EB%9D%BC.md)
* [Util 클래스](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/g.%20Util%20%ED%81%B4%EB%9E%98%EC%8A%A4.md)
* [네이밍](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/h.%20%EB%84%A4%EC%9D%B4%EB%B0%8D.md)
* [함수 규칙](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/i.%20%ED%95%A8%EC%88%98.md)
* [좋은 테스트 코드](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/j.%20%EC%A2%8B%EC%9D%80%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%BD%94%EB%93%9C.md)
* [명시적 프로그래밍](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/k.%20%EB%AA%85%EC%8B%9C%EC%A0%81%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D.md)
* [문서화 가이드](https://github.com/liveforone/study/blob/main/%5B%EB%82%98%EB%A7%8C%EC%9D%98%20%EC%8A%A4%ED%83%80%EC%9D%BC%20%EA%B0%80%EC%9D%B4%EB%93%9C%5D/l.%20%EB%AC%B8%EC%84%9C%ED%99%94%20%EA%B0%80%EC%9D%B4%EB%93%9C.md)

[db설계]
* 인덱스를 적극 사용하되, 4개 이상 걸지 말아라.
erd 넣기
쿼리를 직접 날리는 방식으로 변경(none)
결국 시간이 지날수록 좋은 개발자란
응용을 잘하고, 좋은 설게를 할줄아는것.
단순히 코드를 치는것보단 전체적인 아키텍쳐와 db, 컨벤션 등의 설계를 잘하는 개발자가 진정한 좋은 개발자라 생각함.
이는 ai가 나와도 살아남고 ai를 잘 응용할수있는 개발자라 생각함.
설계(세세한)는 개발자의 가장 큰 덕목임.
따라서 db는 직접 제작한다. create-drop으로 설정해서 만들고나면 무조건 지운다.
☆인덱스는 직접 걸지 아니면 코드로 걸지 생각해보기.

로그를 잘찍기. 얼마가 오갔는지 로그를 확실하게 남겨라


[거래내역]
입/출금 날짜
title이 존재하며 atm일경우 atm을 붙이기
입금이라면 +
출금이라면 - 붙여서 로그 보여주기

[알림]
알림 시스템으로 입금, 출금시 알림 보내고,
알림을 클릭해서 상세조회, 즉 확인을 하게되면 삭제된다.
또한 어드민이 알림을 직접 만들 수 있다.(공지)
이도 위와 마찬가지로 작동한다.

[송금]
정지된 통장인지 확인

송금(뱅킹)(보내는자와 받는자)
송금시 비밀번호 입력받기

[atm]

[대출]
금융상품(=대출) -> 자동이체로 자동 출금(이자붙여서)

[통계]
나의 통계 페이지 접속시 돈을 얼마나 사용했는지, 지난달 대비로 알려준다.
(소득이 얼마나, 지출이 얼마나) 퍼센트 말고 돈으로 계산
가능하다면 전체의 평균에서 나는 얼마나 차이가 나는지도 해보기

해의 마지막에는 이자를 주는데, 이자는 이자 신청 페이지에서 할 수 있다.
이자 신청페이지는 12월 20일부터 31일까지 가능하며,
1월부터 11월까지의 이자를 붙여준다.
입금된돈 - 출금된돈 = 이자를 붙일 돈
이 돈의 1퍼센트를 통장에 입금시켜준다.

[엔티티]
bankbook : 내 자산
member : 통계, 내 정보
loan
record : 거래내역
notice : 알림

[통장이 외부에 제공]
1. 잔액 plus, minus 메서드 제공
2. 통장 번호로 통장 찾는 메서드 제공