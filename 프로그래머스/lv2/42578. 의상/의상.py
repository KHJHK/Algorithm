#1. 옷 이름과 종류가 적힌 배열 뭉치(2차원 배열) 주어짐
#2. 옷 종류별로 옷들을 정리
#3. 종류별 옷의 갯수 구하기
#4. 단일 종류 경우의수 + 2벌 경우의수 + 3벌 경우의수... + N벌 경우의 수 다 더하기

def solution(clothes):
    clothes_type = []
    answer = 1
    
    map = {}
    for key in clothes:
        if key[1] not in map:
            map[key[1]] = 1
        else:
            map[key[1]] += 1
            
    for key in map:
        answer *= map[key] + 1
    
    return answer - 1