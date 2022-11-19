const firebaseConfig = {
    apiKey: "AIzaSyCWxtqm8RXvw3FwPblKW4LqKEbr6LtAino",
    authDomain: "containment-zone-a57a7.firebaseapp.com",
    databaseURL: "https://containment-zone-a57a7-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "containment-zone-a57a7",
    storageBucket: "containment-zone-a57a7.appspot.com",
    messagingSenderId: "862758591971",
    appId: "1:862758591971:web:2ab3dc2e64debff8c954d6",
    measurementId: "G-F2BHFQ61ZN"
  };
  //initialize firebase
  firebase.initializeApp(firebaseConfig);
  //reference
  var containerDB= firebase.database().ref('container')

  document.getElementById('container').addEventListener("SUBMIT",submitForm);

  function submitForm (e){
    e.preventDefault();
    var name = getElementVal('Name')
    var Email = getElementVal('Email')
    var MobileNumber = getElementVal('MobileNumber')
    var Password = getElementVal('Password')
    
    saveMessages(name,Email,MobileNumber,Password);
  }
  const saveMessages = (Name,Email,MobileNumber,Password) => {
     var newcontainer = containerDB.push();

     newcontainer.set({
        Name : Name,
        Email: Email,
        MobileNumber:MobileNumber,
        Password : Password

     })
  };

  const getElementVal =(id) =>{
    return document.getElementById(id).Value;
  };
