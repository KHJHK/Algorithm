-- 코드를 입력하세요
-- 분류별로 가장 비싼 식품 분류, 가격, 이름 조회
-- 과자, 국, 김치, 식용유만 출력
-- 가격 기준 내림차순
SELECT CATEGORY, PRICE as MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT
WHERE CATEGORY in ('과자', '국', '김치', '식용유')
AND PRICE in (SELECT MAX(PRICE) FROM FOOD_PRODUCT GROUP BY CATEGORY)
ORDER BY PRICE DESC