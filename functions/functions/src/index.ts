
// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript
//
// export const helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

import admin = require('firebase-admin');
import functions = require('firebase-functions');
admin.initializeApp();
// const SENDGRID_API_KEY = functions.config().sendgrid.key;

// import sgMail = require('@sendgrid/mail');

// sgMail.setApiKey(SENDGRID_API_KEY);

// exports.firetoreEmail = functions.firestore
// .document('complaints/{cId}')
// .onCreate((snap, context) => {
//   // Get an object representing the document
//   // e.g. {'name': 'Marie', 'age': 66}
//   let newValue = snap.data();
//   if(newValue==null)
//   {
//       console.log("null hai");
      
//       newValue={
//           complaint:"no complaint",
//           email:"ajdesh2000@gmail.com"
//       }
//   }

//   // access a particular field as you would any JS property
//   let complaint1 = newValue.complaint;
//   let email=newValue.email;
  
//   const msg={
//       to:email,
//       from:'complaints@angularfirebase.com',
//       subject: 'New Complaint',

//       templateId:'3e1fd58b3e904281bd0afcb31d7289c5',
//       substitutionWrappers: ['{{','}}'],
//       substitutions:{
//           complaint:complaint1
//       }
      

//   }
//   return sgMail.send(msg)
  
// });


export const SendNotificationToAdmin =
functions.https.onRequest((req,res)=>{
    console.log("event triggered");
    let notifString="Machines:"
    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    let reqd=false;
    admin.firestore().collection('inventory').get().then(qs=>{
        console.log("entering foreach with date");
        console.log(date);
        qs.forEach(ds=>{
            if(ds.get('date')===date)
            {
                reqd=true;
                notifString=notifString+' '+ds.id+',';
            }
        });
        console.log("finished foreach");
        notifString=notifString+' require maintenance';
        console.log("preparing to deploy payload");
        const payload={
            data:{
                name:"Maintenance Reqiured",
                msg:notifString
            },
            topic:"maintenance"
        };
        if(reqd)
        {
            res.send("if - Hello from Firebase!");
            console.log("deploying now");
            admin.messaging().send(payload)
            .then((response) => {
                console.log('This was the notification Feature',response);
                return 0;
            }).catch((error) => {
                console.log(`Error sending Notification`, error);
                throw error;
            });

        }
        else
        {
            res.send("else - Hello from Firebase!");
        }
        
        
    }).catch((err: any)=>{
        console.log(err);
        res.send("Hello from the catch block!");
    })


});