class CountWord():
    def __init__(self, words):
        self.words = words

    def bind_event(self, words):
        count_number = 0
        for i in words:
            for j in i:
                count_number += 1
        return count_number