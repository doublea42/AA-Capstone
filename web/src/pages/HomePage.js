import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class HomePage extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage', 'redirectProfilePage',
                                'redirectMyPetsPage', 'redirectRentPage', 'clientLoaded', 'loadPets',
                            'loadProfile', 'loadPet', 'loadREntalHistory', 'loadActive', 'pageCreatePet',
                        'pageCreateActive', 'pageUpdateProfile', 'pageUpdatePet', 'pageRemovePet'
                    ,'pageRemoveActiveRental', 'pageCreateProfile', 'getHTMLForSearchResults'], this)


        this.client = new PawDorableClient();

    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        // document.getElementById('rent').addEventListener('click', this.redirectRentPage);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPetsPage);
        // document.getElementById('test').addEventListener('click', this.redirectTest);
        
        this.clientLoaded();
        this.loadPets(); //this works
        // this.loadProfile(); //this works
        // this.loadPet(); //this works
        // this.loadREntalHistory();  //thisworks
        // this.loadActive();  //thisworks
        // this.pageCreateProfile(); // this works
        // this.pageCreatePet(); // this works
        // this.pageCreateActive(); //this works
        // this.pageUpdateProfile(); //this works
        // this.pageUpdatePet(); // this works
        // this.pageRemovePet(); // this works
        // this.pageRemoveActiveRental(); //this works
        
    }

    async clientLoaded(){
        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        
    }

    async loadPets(){
        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        console.log(profileEmail);
        const newDiv = document.createElement("div");

        const allPets = await this.client.getAllPets();
        console.log(allPets);
        const string = JSON.stringify(allPets)

        // if(allPets == null){
        //     newDiv.innerText = "No Pets Found"
        // }

        newDiv.innerHTML = this.getHTMLForSearchResults(allPets);

        // console.log(string);
        // newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }

    getHTMLForSearchResults(searchResults) {
        console.log(searchResults , "from getHTMLForSearchResults");
            //    if (!searchResults || !searchResults.allEventList || searchResults.allEventList.length === 0) {
            //        return '<h4>No results found</h4>';
            //    }
               let html = "";
               for (const res of searchResults) {
                    console.log(res);
                   html += `
                   <tr>
                   <td>
                            ${res.id}
                    </td>
                    <td>
                            ${res.name}
                     </td>
                     <td>
                            ${res.kindOfPet}
                      </td>
                       <td>
                            ${res.age}
                     </td>
                     <td>
                             ${res.gender}
                    </td>
                   </tr><br>`;
               }
               return html;
           }

    async loadProfile(){


        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        console.log(profileEmail);
        const newDiv = document.createElement("div");
        const allPets = await this.client.getProfile(profileEmail);
        const string = JSON.stringify(allPets)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);
        
        
    }


    async loadPet(){
        const petID = "PET_0006";
        console.log(petID);
        const newDiv = document.createElement("div");
        const Pet = await this.client.getPet(petID);
        const string = JSON.stringify(Pet)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }

    async loadREntalHistory(){
        const ID = "RH_0001";
        console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.getRentalHistory(ID);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }
    async loadActive(){
        const ID = "AR_RH_0001";
        console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.getActiveRental(ID);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }

    async pageCreatePet(){
        const a = "Zeus";
        const b = "DOG";
        const c = "5";
        const d = "MALE";
        const e = "true";
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.CreatePet(a,b,c,d,e);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }

    async pageCreateActive(){
        const a = "PET_0007";
        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.CreateActiveRental(a, profileEmail);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }


    async pageUpdateProfile(){

        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        const a = "felipe";
        const b = "something";
        const c = "50";
        
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.UpdateProfile(profileEmail,a,b,c);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }


    async pageUpdatePet(){
        const a = "PET_8FIIN";
        const b = "true";
        
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.UpdatePet(a,b);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }

    async pageRemovePet(){
        const a = "PET_ZKXRW";
        
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.RemovePet(a);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }

    async pageRemoveActiveRental(){
        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        const a = "PET_YVCWM";
        const b = profileEmail;
        const c = "5";
        
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.RemoveActiveRental(a,b,c);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }


    async pageCreateProfile(){
        const a = "Anndres";
        const b = "felipe";
        const c = "31";
        
        // console.log(ID);
        const newDiv = document.createElement("div");
        const object = await this.client.CreateProfile(a,b,c);
        const string = JSON.stringify(object)
        console.log(string);
        newDiv.append(string);
        const currentDiv = document.getElementById("result-body");
        document.body.insertBefore(newDiv, currentDiv);


    }









    async login(){
        await this.client.login();
    }

    logout(){
        this.client.logout();
        window.location.href = '/index.html';

    }

    redirectHomePage(){
        window.location.href = '/HomePage.html';
    }
    redirectProfilePage(){
        window.location.href = '/Profile.html';
    }
    redirectMyPetsPage(){
        window.location.href = '/MyPets.html';
    }
    redirectRentPage(){
        window.location.href = '/ComingHome.html';
    }
    // redirectTest(){
    //     window.location.href = '/test.html';
    // }

   

}

const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);