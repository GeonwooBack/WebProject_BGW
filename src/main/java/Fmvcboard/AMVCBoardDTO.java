package Fmvcboard; // 패키지명 유지

// DTO(Data Transfer Object) 클래스: 게시물 정보를 저장하고 전달
public class AMVCBoardDTO {
    
    // 멤버 변수 선언: mvcboard 테이블 컬럼과 일치
    private String idx;        // 게시물 번호
    private String id;         // 작성자 ID
    private String title;      // 게시물 제목
    private String content;    // 게시물 내용
    private java.sql.Date postdate; // 작성일
    private String ofile;      // 원본 파일명
    private String sfile;      // 저장된 파일명
    private int downcount;     // 다운로드 횟수
    private int visitcount;    // 조회수

    // 추가: 회원 테이블과 join하여 이름을 저장하기 위한 변수
    private String name; 

    // 기본 생성자 (필요한 경우 정의)
    public AMVCBoardDTO() {}

    // Getter 및 Setter 메서드
    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Date getPostdate() {
        return postdate;
    }

    public void setPostdate(java.sql.Date postdate) {
        this.postdate = postdate;
    }

    public String getOfile() {
        return ofile;
    }

    public void setOfile(String ofile) {
        this.ofile = ofile;
    }

    public String getSfile() {
        return sfile;
    }

    public void setSfile(String sfile) {
        this.sfile = sfile;
    }

    public int getDowncount() {
        return downcount;
    }

    public void setDowncount(int downcount) {
        this.downcount = downcount;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
