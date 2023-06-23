import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";
import Authenticator from "../api/authenticator";

class Profile extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'login', 'logout', 'redirectHomePage','redirectMyPets',
         'redirectUpdateProfile', 'redirectComingHome', 'redirectBuddies', 'redirectBestBuddies',
          'redirectProfilePage', 'loadProfile', 'loadProfileCheck', 'getHTMLForSearchResults'], this)
        this.client = new PawDorableClient();
    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('log-out').addEventListener('click', this.logout);
        // document.getElementById('coming-home').addEventListener('click', this.redirectComingHome);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPets);
        document.getElementById('update-profile').addEventListener('click', this.redirectUpdateProfile);
        // document.getElementById('buddies').addEventListener('click', this.redirectBuddies);
        // document.getElementById('best-buddies').addEventListener('click', this.redirectBestBuddies);
        // document.getElementById('coming-home-1').addEventListener('click', this.redirectComingHome);
        document.getElementById('my-pets-1').addEventListener('click', this.redirectMyPets);


        // this.loadProfileCheck();
        this.loadProfile();
    }


    async loadProfileCheck(){
        const profile = null;
        try{
            profile = this.client.getProfile();

        }catch{}
        console.log(profile);

        if(profile == null){
            this.redirectUpdateProfile();
        }

    }

    async loadProfile(){


        const identity = await this.client.getIdentity();
        const profileEmail = identity.email;
        console.log(profileEmail);
        const newDiv = document.createElement("div");
        const profileInfo = await this.client.getProfile(profileEmail);
        const string = JSON.stringify(profileInfo)
        console.log(profileInfo);

        newDiv.innerHTML = this.getHTMLForSearchResults(profileInfo);

        // const string = JSON.stringify(allPets)
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
                    console.log(searchResults);
                   html += `
                   <tr>
                   <td>
                            ${searchResults.firstName}
                    </td><br>
                    <td>
                            ${searchResults.lastName}
                     </td><br>
                     <td>
                            ${searchResults.age}
                      </td><br>
                       <td>
                            ${searchResults.myPets}
                     </td>
                   </tr><br>`;
               return html;
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

    redirectMyPets(){
        window.location.href = '/MyPets.html';
    }

    redirectUpdateProfile(){
        window.location.href = '/Create_Update_Profile.html';
    }

    redirectComingHome(){
        window.location.href = '/ComingHome.html';
    }

    redirectBuddies(){
        window.location.href = '/Buddies.html';
    }

    redirectBestBuddies(){
        window.location.href = '/BestBuddies.html';
    }


   

}

const main = async () => {
    const Page = new Profile();
    Page.mount();
};

window.addEventListener('DOMContentLoaded', main);