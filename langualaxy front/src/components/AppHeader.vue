  <template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-4">
      <div class="container">        
        <div id="head-logo" class="navbar-brand mx-auto">
          <router-link class="nav-link" to="/">
            <img src="../assets/langualaxy_logo.jpeg" alt="Logo" class="rounded-circle img-fluid logo">
            <span id="logo_text" class="text-white" >Langualaxy</span>
          </router-link>
        </div>
        <!-- <form class="form-inline mx-auto">
          <input class="form-control bg-dark text-white mr-sm-2" type="search" placeholder="Type here to search" aria-label="Search">
        </form> -->
        <div id="head-nav" class="navbar-nav mx-auto">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link to="/" class="nav-link" >Home</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/tests" class="nav-link">Tests</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/lessons" class="nav-link">Learn</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/games" class="nav-link">Games</router-link>
            </li>
          </ul>
        </div>
        <div id="head-login" class="mx-auto ">
          <img id="avatar" src="https://placehold.co/30x30" alt="Profile Icon" class="rounded-circle mr-2">
          <router-link to="/login" class="nav-link"><button id="login" class="btn btn-primary">Log in</button></router-link> 
        </div>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    name: 'AppHeader'
  }
  </script>
  
  <style scoped>
  li{
    margin-left: 10px;
    margin-right: 10px;
  }
  
  .nav-link{
    font-size: 20px;
    color: white;
  }
  .nav-link:hover{
    color: #AFAFAF
  }
  a.router-link-active {
  color: #D6FF30;
  }
  a.router-link-active:hover{
    color: #D6FF30;
  }
  #head-login{
    width: 20vb;
  }
  #head-logo{
    width: 20vb;
  }
  nav{
    font-family: 'Patua One', sans-serif;
  }
  #logo_text{

    margin-left: 5px;
  }
  #login {
    background-color: #5930FF;
    border: #5930FF;
    display: block;
  }
  #login:active{
    transform: scale(0.9);
  }
  #avatar{
    display: none;
  }
  .logo {
  width: 50px;
  height: 50px;
  }
  </style>
  
