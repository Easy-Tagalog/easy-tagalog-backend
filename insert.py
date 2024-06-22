import requests
import json


def main():
    url = "http://localhost:8000/api/words/"

    with open('./data/lessonOneWords.json') as file:
        words = json.load(file)

        for word in words:
            requests.post(url=url, json=word)


if __name__ == "__main__":
    main()
