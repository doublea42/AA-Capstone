import BindingClass from "../util/bindingClass";
import PawDorableClient from "../api/pawDorableClient";
import DataStore  from "../util/DataStore";
import Header from "../components/header";

class HomePage extends BindingClass{

    constructor(){
        super();

        this.bindClassMethods(['mount', 'addHeaderToPage', 'login', 'logout',
         'redirectHomePage', 'redirectProfilePage', 'redirectMyPetsPage', 'redirectRentPage', 'clientLoaded'], this)

        // this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        // this.header = new Header(this.dataStore);

        this.client = new PawDorableClient();

    }

    mount(){
        document.getElementById('home-page').addEventListener('click', this.redirectHomePage);
        document.getElementById('sign-up').addEventListener('click', this.login);
        document.getElementById('log-out').addEventListener('click', this.logout);
        document.getElementById('profile').addEventListener('click', this.redirectProfilePage);
        document.getElementById('my-pets').addEventListener('click', this.redirectMyPetsPage);
        document.getElementById('rent').addEventListener('click', this.redirectRentPage);
        
    }

    // async addHeaderToPage() {
    //     const currentUser = await this.client.getIdentity();

    //     const siteTitle = this.createSiteTitle();
    //     // const userInfo = this.createUserInfoForHeader(currentUser);

    //     const header = document.getElementById('header');
    //     header.appendChild(siteTitle);
    //     header.appendChild(userInfo);
    // }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    // async clientLoaded() {
    //     const urlParams = new URLSearchParams(window.location.search);
    //     const playlistId = urlParams.get('id');
    //     document.getElementById('playlist-name').innerText = "Loading Playlist ...";
    //     const playlist = await this.client.getPlaylist(playlistId);
    //     this.dataStore.set('playlist', playlist);
    //     document.getElementById('songs').innerText = "(loading songs...)";
    //     const songs = await this.client.getPlaylistSongs(playlistId);
    //     this.dataStore.set('songs', songs);
    // }





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

   

}

const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);