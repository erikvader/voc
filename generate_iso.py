import datetime
import random


def generate_isos(count):
    for n in range(count):
        delta = datetime.timedelta(days=random.randint(-200, 200), hours=random.randint(-11, 11), seconds=random.randint(-60, 60))
        d = datetime.datetime.today() + delta
        d = d - datetime.timedelta(microseconds=int(d.microsecond / 1000) * 1000)
        iso = d.isoformat()
        if len(iso) == 26:
            yield iso


if __name__ == "__main__":
    n = 250001
    with open('isos-%d.txt' % n, 'w+') as f:
        f.write(','.join(generate_isos(n)))