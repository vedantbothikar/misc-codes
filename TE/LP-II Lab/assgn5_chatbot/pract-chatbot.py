from nltk.chat.util import Chat, reflections

pairs = [

    [
        r'what is your name?',
        ['My name is chatbot, my name is chatty']
    ],
    [
        r'what is your age?',
        ['It has been 2 years since my development']
    ],
    [
        r'(.*) created you?',
        ['Vedant created me. Thanks to him']
    ]

]


def chatty():
    print('chat with me: ')
    chat = Chat(pairs, reflections)
    chat.converse()


chatty()