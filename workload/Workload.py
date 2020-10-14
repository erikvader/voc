import datetime
import random

n = 500000
for i in range(n):
    timedelta = datetime.timedelta(1, 1, 1)
    timedelta2 = datetime.timedelta(2, 2, 2)

    timedelta = timedelta * 2
    timedelta2 = timedelta + timedelta
    timedelta = timedelta % timedelta2
    test = str(timedelta)


print("done")
