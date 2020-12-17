-- 게시판 글번호용 시퀀스
CREATE SEQUENCE seq_board_bno
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9999999999
    NOCYCLE ;

-- 게시판 테이블 생성
CREATE TABLE Board
(
    bno         NUMBER(10),
    title       VARCHAR2(200)   CONSTRAINT board_title_nn NOT NULL,
    content     VARCHAR2(2000)   CONSTRAINT board_content_nn NOT NULL,
    email       VARCHAR2(100),
    regdate     DATE   DEFAULT   SYSDATE   CONSTRAINT board_regdate_nn NOT NULL,
    readnum     NUMBER(6)   DEFAULT 0   CONSTRAINT board_readnum_nn NOT NULL,
    userid      VARCHAR2(12)   CONSTRAINT board_userid_nn NOT NULL,
    CONSTRAINT board_bno_pk  PRIMARY KEY(bno),
    CONSTRAINT board_userid_fk FOREIGN KEY(userid)
    REFERENCES Users(userid)
)

-- 게시판 목록 가져오기
CREATE OR REPLACE PROCEDURE sp_bbs_select_all
(
    bbs_record OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN bbs_record FOR
    SELECT bno, title, email, TO_CHAR(regdate,'YYYY-MM-DD') AS writeday, readnum, name
    FROM board NATURAL JOIN users
    ORDER BY bno DESC;
END;


-- 삽입
CREATE OR REPLACE PROCEDURE sp_bbs_insert
(
    v_title IN Board.title%TYPE,
    v_content IN Board.content%TYPE,
    v_email IN Board.email%TYPE,
    v_userid IN Board.userid%TYPE
)
IS
BEGIN
    INSERT INTO Board(bno, title, content, email, userid)
    VALUES(SEQ_BOARD_BNO.NEXTVAL, v_title, v_content, v_email, v_userid);
    COMMIT;
END;