-- 코드를 입력하세요
-- 최다 리뷰작성자의 리뷰 조회
-- MEMBER_NAME, REVIEW_TEXT, REVIEW_DATE 출력
-- REVIEW_DATE 기준 오름차순, REVIEW_TEXT 기준 내림차순

SELECT M.MEMBER_NAME, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM MEMBER_PROFILE AS M
JOIN REST_REVIEW AS R
ON M.MEMBER_ID = R.MEMBER_ID
WHERE M.MEMBER_ID IN (SELECT MEMBER_ID
                    FROM REST_REVIEW
                    GROUP BY MEMBER_ID
                    HAVING COUNT(REVIEW_ID) = (SELECT COUNT(REVIEW_ID)
                                               FROM REST_REVIEW
                                               GROUP BY MEMBER_ID
                                               ORDER BY COUNT(REVIEW_ID) DESC
                                               LIMIT 1))
ORDER BY R.REVIEW_DATE ASC, R.REVIEW_TEXT ASC