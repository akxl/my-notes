
if __name__ == '__main__':

    f = open('my_text_file.txt')
    print(f.read())
    f.close()

    f = open('my_text_file.txt')
    a = iter(f)
    print(next(a))
    print(next(a))
    print(next(a))
    print(next(a))
    print(next(a))
    f.close()

    f = open('my_text_file.txt')
    for line in f:
        print(line)
    f.close()

    with open('my_text_file.txt') as f:
        for line in f:
            print(line)