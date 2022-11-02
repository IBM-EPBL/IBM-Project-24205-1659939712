from flask import Flask

app=Flask(__name__)

@app.route('/')
def home():
    return 'login.html'

@app.route('/register')
def about():
    return 'register.html'


@app.route('/mlogin')
def about():
    return 'mlogin.html'

@app.route('/mregister')
def about():
    return 'mregister.html'

if __name__=="__main__":
    app.run(debug=True)