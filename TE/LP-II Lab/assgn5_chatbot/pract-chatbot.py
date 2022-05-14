from nltk.chat.util import Chat, reflections

pairs = [
    [
        r'(.*) name?',
        ['Hi my name is Chatty']
    ],
    [
        r'(.*) your age?',
        ['Hi my age is 2 years']
    ]
]

def chatty():
    print('type lowercase-')
    chat = Chat(pairs, reflections)
    chat.converse()

chatty()