-- 코드를 입력하세요
# 1. 통풍시트, 열선시트, 가죽시트중 하나 이상 옵션 포함
# 2. 종류 별로 몇 대 인지 표시
# 3. 자동차 수 컬럼은 CARS로 지정
SELECT C.CAR_TYPE AS CAR_TYPE, COUNT(*) AS 'CARS'
FROM(
        SELECT *
        FROM CAR_RENTAL_COMPANY_CAR
        WHERE (OPTIONS LIKE '%통풍시트%')
        OR (OPTIONS LIKE '%열선시트%')
        OR (OPTIONS LIKE '%가죽시트%')
    ) AS C
GROUP BY C.CAR_TYPE
ORDER BY C.CAR_TYPE ASC