QUESTIONS = [
    'Do you have cough?',
    'Do you have a sore throat?',
    'Do you have a fever?',
    'Are you noticing any unexplained excessive sweating?',
    'Do you have an itchy throat?',
    'Do you have a runny nose?',
    'Do you have a stuffy nose?',
    'Do you have a headache?',
    'Do you feel tired without actually exhausting yourself?'
]


THRESHOLD = {
    'Mild' : 30,
    'Moderate' : 50,
    'Severe': 75
}


def expertSystem(questions, threshold):
    score = 0

    for question in questions:
        print(question)
        ans = input('Answer y/n')

        if(ans.lower() == 'y'):
            level = input('On a scale of 0 to 10, how severe is it?')
            score += int(level)

    print()

    if score >= threshold['Severe']:
        print('You are in a severe condition')
    elif score >= threshold['Moderate']:
        print('moderate')
    elif score >= threshold['Mild']:
        print('Mild')
    else:
        print('nothing')


expertSystem(QUESTIONS, THRESHOLD)