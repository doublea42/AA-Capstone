const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    index: path.resolve(__dirname, 'src', 'pages', 'index.js'),
    HomePage: path.resolve(__dirname, 'src', 'pages', 'HomePage.js'),
    CreateUpdateProfile: path.resolve(__dirname, 'src', 'pages', 'CreateUpdateProfile.js'),
    Profile: path.resolve(__dirname, 'src', 'pages', 'Profile.js'),
    PetPage: path.resolve(__dirname, 'src', 'pages', 'PetPage.js'),
    ReturnPet: path.resolve(__dirname, 'src', 'pages', 'ReturnPet.js'),
    MyPets: path.resolve(__dirname, 'src', 'pages', 'MyPets.js'),
    AddPet: path.resolve(__dirname, 'src', 'pages', 'AddPet.js'),
    MyPetProfile: path.resolve(__dirname, 'src', 'pages', 'MyPetProfile.js'),
    ComingHome: path.resolve(__dirname, 'src', 'pages', 'ComingHome.js'),
    Buddies: path.resolve(__dirname, 'src', 'pages', 'Buddies.js'),
    BestBuddies: path.resolve(__dirname, 'src', 'pages', 'BestBuddies.js'),
    // TestFuctions: path.resolve(__dirname, 'src', 'pages', 'TestFuctions.js'),
    // Test: path.resolve(__dirname, 'src', 'pages', 'Test.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}