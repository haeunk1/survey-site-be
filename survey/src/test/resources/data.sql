--설문조사 게시글 저장
INSERT INTO Survey (survey_id,member_id, title, type, per_point, limit_submit, startdate, enddate, created_at, updated_at, delete_yn)
values(1,1,'급식 설문조사','OX',10,10,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,NULL,'N');
