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
  var MNDB= firebase.database().ref('MN')

  document.getElementById('MN').addEventListener("SUBMIT",submitForm);

  function submitForm (e){
    e.preventDefault();
    var MobileNumber = getElementVal('MobileNumber')
    
     
    
    saveMessages(MobileNumber);
  }
  const saveMessages = (MobileNumber) => {
     var MN = MNDB.push();

     newMN.set({
        MobileNumber :MobileNumber,
         


     })
  };

  const getElementVal =(id) =>{
    return document.getElementById(id).Value;
  };
