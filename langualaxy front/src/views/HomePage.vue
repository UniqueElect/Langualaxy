<template>
<body>
    <div id="welcome">
        <h1 id="welcome-text" class="text-center display-4 font-weight-bold">WELCOME TO <p>LANGUALAXY</p></h1>
    </div>
    <div class="container py-5">
        <h2 class="text-center mb-4">LEARNING ENGLISH ONLINE</h2>
        <p id="topic" class="text-center">Grammar, Vocabulary, Exercises, Tests, Games and more</p>
        <p id="u-topic" class="text-center mb-5">You will find a lot of information about the English language on this website. You can learn English words, practice grammar, look at some basic rules, prepare for exams, do tests or just have fun playing games. Enjoy yourself with more than 1,000 exercises online.</p>
        <div class="row">
            <div class="col-md-6 col-lg-4 mb-4">
                 <div class="bg-zinc-800 p-4 rounded-lg d-flex flex-column align-items-stretch h-100">
                    <img src="../assets/pen.png" class="img-fluid logo">
                    <h3 class="font-weight-bold mb-3">Grammar & Vocabulary</h3>
                    <p class="mb-4">You can look up basic grammar rules in our Grammar section. We provide vocabulary lists, useful phrases and sentences, idioms, sample letters, phrasal verbs and much more.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 mb-4">
                 <div class="bg-zinc-800 p-4 rounded-lg d-flex flex-column align-items-stretch h-100"> 
                    <img src="../assets/test.png" class="img-fluid logo">
                    <h3 class="font-weight-bold mb-3">Tests & Exams</h3>
                    <p class="mb-4">Check your English with our Tests and Complex Tests. You can prepare for exams with our Exercises for Exams.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 mb-4">
                 <div class="bg-zinc-800 p-4 rounded-lg d-flex flex-column align-items-stretch h-100">
                    <img src="../assets/graduation-hat.png" class="img-fluid logo">
                    <h3 class="font-weight-bold mb-3">English & School</h3>
                    <p class="mb-4">You can download Worksheets for students. We also provide flashcards  system for you, to create or find falshcards to learn vocabulary. You can find our exercises selected in Levels.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 mb-4">
                 <div class="bg-zinc-800 p-4 rounded-lg d-flex flex-column align-items-stretch h-100">
                    <img src="../assets/dice-cube.png" class="img-fluid logo">
                    <h3 class="font-weight-bold mb-3">English & Free Time</h3>
                    <p class="mb-4">In our Games section you can solve Crosswords, play Hangman or Find pairs of words and pictures. Read News in English or English Texts. You can also find movies/podcasts grouped by English level.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 mb-4">
                 <div class="bg-zinc-800 p-4 rounded-lg d-flex flex-column align-items-stretch h-100">
                    <img src="../assets/pointer.png" class="img-fluid logo">
                    <h3 class="font-weight-bold mb-3">Roadmap</h3>
                    <p class="mb-4">Use our roadmap to understand and plan your way of learning English language. We divide it on six parts, you can improve each of them using our website.</p>
                </div>
            </div>
            <div class="col-md-6 col-lg-4 mb-4">
                 <div class="bg-zinc-800 p-4 rounded-lg d-flex flex-column align-items-stretch h-100">
                    <img src="../assets/user.png" class="img-fluid logo">
                    <h3 class="font-weight-bold mb-3">Account benefits</h3>
                    <p class="mb-4">Sign up to create account and start tracking your progress!</p>
                </div>
            </div>
        </div>
        <h2 class="text-center mt-5 mb-4">What's new?</h2>
        <div id="delimetr"></div>
        <ul class="list-unstyled mb-5">
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
            <li>Update</li>
        </ul>
    </div>
</body>

</template>

<script>
  export default {
    

  }
</script>

<style lang="css" scoped>
    #delimetr{
        height: 4px;
        background-color: #5930FF;
        margin-bottom: 20px;
    }
    .logo{
        margin-bottom: 5px;
        height: 50px;
        width: 50px;
        align-self: center;
    }
    #topic{
        color: #5930FF;
        font-size: 20px
    }
    li:before{
        content: "\2022";
        margin-right: 7px;
    }
    h2{
        color:#CDFF03;
    }
    h3{
        text-align: center;
        color: #5930FF;
    }
    body{
        color: #AFAFAF;
        font-family:'Times New Roman', Times, serif;
        background-color: #0F1211;
    }
    #welcome-text{
        font-family: 'Jockey One', sans-serif;
        white-space: pre-wrap;
        color: transparent;
        -webkit-text-stroke: 1px #CDFF03;
        font-size: 128px;
        font-weight: 500;
        letter-spacing: 2px;
    }
    #welcome{
        display: flex; justify-content: center; align-items: center;
        background-image: url('../assets/background_stars.png'); 
        background-size: cover;
        min-height: calc(100vh - 114px);
        background-repeat: no-repeat; 
        background-position: center center; 
    }
   .bg-zinc-800 {
            background-color: #202020;
        }
</style>