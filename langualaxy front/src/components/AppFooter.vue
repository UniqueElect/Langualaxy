<template>
    <footer class="bg-dark text-white p-4">
        <div class="container">
            <div class="row justify-content-between align-items-end">
                <div class="col-md-auto text-start">
                    <router-link to="/">
                        <img src="../assets/langualaxy_logo.jpeg" alt="Logo" class="rounded-circle img-fluid logo">
                    </router-link>
                </div>
                <div class="col-md-auto text-center">
                    <p id="copyr" class="mb-0">Copyright © 2024 Langualaxy All rights reserved.</p>
                </div>
                <div class="col-md-auto text-end align-self-end">
                    <div id="list" class="d-flex flex-column">
                        <router-link class="nav-link mb-2" to="/webs">Useful websites</router-link>
                        <router-link class="nav-link mb-2" to="/terms">Terms and policies</router-link>
                        <router-link class="nav-link" to="/about" >About us</router-link>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'AppFooter'
}
</script>

<style scoped>
    #copyr{
        color: #B3A0FF;
    }
    .nav-link{
        color: #D6FF30;
    }
    .nav-link:hover{
        color: white;
    }
    footer{
        font-family:'Times New Roman', Times, serif;
    }

</style>
