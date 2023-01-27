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
* [거래내역시스템](https://github.com/liveforone/intelligent_bank/wiki/%EA%B1%B0%EB%9E%98%EB%82%B4%EC%97%AD-%EC%8B%9C%EC%8A%A4%ED%85%9C)
* [송금시스템](https://github.com/liveforone/intelligent_bank/wiki/%EC%86%A1%EA%B8%88-%EC%8B%9C%EC%8A%A4%ED%85%9C)
* [ATM시스템](https://github.com/liveforone/intelligent_bank/wiki/ATM-%EC%8B%9C%EC%8A%A4%ED%85%9C)
* [정산통계시스템]()
### d. 데이터 베이스 설계
* [데이터 베이스 설계 위키](https://github.com/liveforone/intelligent_bank/wiki/%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B2%A0%EC%9D%B4%EC%8A%A4-%EC%84%A4%EA%B3%84-%EB%B0%8F-%EC%9B%90%EC%B9%99)
### e. 고민한 점
* [날짜로 어떻게 조횔할까?(복잡한 조건절)](https://github.com/liveforone/intelligent_bank/wiki/%EB%82%A0%EC%A7%9C%EB%A1%9C-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%A1%B0%ED%9A%94%ED%95%A0%EA%B9%8C%3F(%EB%B3%B5%EC%9E%A1%ED%95%9C-%EC%A1%B0%EA%B1%B4%EC%A0%88))

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


[통계]
인덱스 걸기

나의 통계 페이지 접속
이번달 지출 한 돈
이번달 수입 한 돈 

total 통계 접속
지금까지의 지출 한 돈
지금까지의 수입 한 돈

해의 마지막에는 이자를 주는데, 이자는 이자 신청 페이지에서 할 수 있다.
이자 신청페이지는 12월 1일부터 31일까지 가능하며,
1월부터 11월까지의 이자를 붙여준다.
입금된돈 - 출금된돈 = 이자를 붙일 돈
이 돈의 1퍼센트를 통장에 입금시켜준다.

통계 시스템을 만들면서 느낀건,
통계를 짜려면 상당히 세부적으로 DB에 저장되어있어야한다는 것을 배웠다.
날짜를 저장할때 년, 월, 일을 한번에 DB에 저장하는 그런 timestamp 같이 저장하는게 아니라
년 따로 월 따로 일따로 저장해야한다는 것을 느꼈다.
그래야 통계를 내기 쉽고, 인덱스도 태우기 쉽고, 무엇보다 조건을 걸기 쉽다는 것을 알았기 때문이다.
장소를 예로들면 도, 시, 군, 구 등으로 세부적으로 분류해서 저장하는것이 저장 공간은 많이 소모하더라더
나중에 세부적으로 검색을 걸거나 인덱스를 태우거나, 통계를 내기에 좋은 것 같다.
앞으로 통계가 필요한 테이블이나, 검색 조건이 복잡한 테이블들은 DB 공간을 조금 더 소모하더라도
세부적으로 컬럼을 쪼개는 것으로 한다. -> 스타일 가이드 작성