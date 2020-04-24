if __name__ == '__main__':
    
    scores = [97, 92, 81, 60]
    scores_more_than_80 = [score for score in scores if score > 80]

    scores_more_than_50_and_is_even = [score for score in scores if score > 50 if score % 2 == 0]

    double_score = [2 * score for score in scores]
    double_score_from_generator = list((2 * score for score in scores))