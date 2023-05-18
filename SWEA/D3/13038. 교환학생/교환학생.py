t = int(input())
for case in range(1, t + 1):
    n = int(input())
    answer = 0
    days = list(map(int, input().split()))
    if n == 1:
        answer = 1
    else:
        #1. 최적의 days 구함
        best_day = [[], []]
        for _ in range(7):
            d = days.pop(0)
            days.append(d)
            if days[0] == 1:
                best_day[0].append(7 - (days[::-1]).index(1) - days.index(1))
                best_day[1].append(days[:])

        days = best_day[1][best_day[0].index(min(best_day[0]))]

        # 2. 수강 날짜 최소값 구하기
        count_class = days.count(1)
        if count_class < n:
            answer_arr = []
            for i in range(len(best_day[1])):
                days = best_day[1][i]
                study_day = 0
                if n % count_class == 0:
                    answer = ((n // count_class) - 1) * 7
                    study_day = ((n // count_class) - 1) * count_class
                else:
                    answer = (n // count_class) * 7
                    study_day = (n // count_class) * count_class

                for day in days:
                    answer += 1
                    study_day += day
                    if study_day == n:
                        break
                answer_arr.append(answer)
            answer = min(answer_arr)
        else:
            answer = 7
            class_day_index = []
            for i in range(len(days)):
                if days[i] == 1:
                    class_day_index.append(i)
            for i in range(len(class_day_index) - 1):
                for j in range(i + 1, len(class_day_index)):
                    if j - i == n - 1:
                        answer = min(answer, class_day_index[j] - class_day_index[i] + 1)
    print(f'#{case} {answer}')