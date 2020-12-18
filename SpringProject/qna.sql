--QnA게시판 글번호용 시퀀스
CREATE SEQUENCE seq_qna_bno
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 9999999999
    NOCYCLE;

--게시판 테이블 생성
CREATE TABLE QnA
(
    bno         NUMBER(10),
    writer      VARCHAR2(20)    CONSTRAINT QnA_writer_nn NOT NULL,
    title       VARCHAR2(200)   CONSTRAINT QnA_title_nn NOT NULL,
    content     VARCHAR2(2000)   CONSTRAINT QnA_content_nn NOT NULL,
    email       VARCHAR2(100),
    regdate     DATE   DEFAULT   SYSDATE   CONSTRAINT QnA_regdate_nn NOT NULL,
    readnum     NUMBER(6)   DEFAULT 0   CONSTRAINT QnA_readnum_nn NOT NULL,
    grp         NUMBER(10)    CONSTRAINT QnA_grp_nn NOT NULL,
    lvl         NUMBER(1)      DEFAULT 0    CONSTRAINT QnA_lvl_nn NOT NULL,
    step        NUMBER(4)      DEFAULT 0    CONSTRAINT QnA_step_nn NOT NULL,
    CONSTRAINT QnA_bno_pk  PRIMARY KEY(bno)
)

--게시판 목록 가져오기
CREATE OR REPLACE PROCEDURE sp_bbs_select_all
(
    bbs_record    OUT   SYS_REFCURSOR
)
AS
BEGIN
    OPEN bbs_record FOR
    SELECT bno, title, email, TO_CHAR(regdate, 'YYYY-MM-DD') AS writeday, readnum, name, userid
    FROM Board NATURAL JOIN Users 
    ORDER BY bno DESC;
END;

--새로운 글 입력하기
CREATE OR REPLACE PROCEDURE sp_qna_insert
(
    v_writer     IN     QnA.writer%TYPE,
    v_title      IN     QnA.title%TYPE,
    v_content    IN     QnA.content%TYPE,
    v_email      IN     QnA.email%TYPE
)
IS
    t_bno      QnA.bno%TYPE;
BEGIN
    INSERT INTO QnA(bno, writer, title, content, email, grp, lvl, step)
    VALUES(seq_qna_bno.NEXTVAL, v_writer, v_title, v_content, v_email, 0, 0, 0);
    COMMIT;

    SELECT MAX(bno) INTO t_bno
    FROM QnA;

    UPDATE QnA SET grp = t_bno
    WHERE bno = t_bno;
    COMMIT;
END;

--글 번호로 한개의 게시판 글 가져오기
CREATE OR REPLACE PROCEDURE sp_bbs_select_one
(
    v_bno          IN       Board.bno%TYPE,
    bbs_record     OUT      SYS_REFCURSOR
)
AS
BEGIN
    OPEN bbs_record FOR
    SELECT bno, title, content, email, TO_CHAR(regdate, 'YYYY-MM-DD') AS writeday,
           readnum, userid, name
    FROM Board INNER JOIN Users USING(userid)
    WHERE bno = v_bno;
END;

--글 번호로 조회수 증가하기
CREATE OR REPLACE PROCEDURE sp_bbs_readnum_update
(
    v_bno          IN       Board.bno%TYPE
)
IS
BEGIN
    UPDATE Board
    SET readnum = readnum + 1
    WHERE bno = v_bno;
    COMMIT;
END;

--게시판 글 수정하기
CREATE OR REPLACE PROCEDURE sp_bbs_update
(
    v_title       IN      Board.title%TYPE,
    v_content     IN      Board.content%TYPE,
    v_email       IN      Board.email%TYPE,
    v_bno         IN      Board.bno%TYPE
)
IS
BEGIN
    UPDATE Board
    SET title = v_title, content = v_content, email = v_email
    WHERE bno = v_bno;
    
    COMMIT;
END;