<template>
    <div class="article-card-box">
        <div class="article-title-box">
            <h1>
                {{ article.title }}
            </h1>
        </div>
        <h3>
            {{ article.subTitle }}
        </h3>
        <p>
            {{ article.description }}
        </p>
        <div class="article-card-content">
            <span>
                {{ article.context }}
            </span>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        article: {
            type: Object,
            default: {
                title: 'Title',
                subTitle: 'Sub title'
            }
        }
    },
    data(){

    }
}
</script>

<style scoped>
.article-card-box{
    padding: 5%;
}
.article-card-content{
    height: 190px;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 8;
    text-overflow: ellipsis;
}
.article-title-box{
    overflow: hidden;
    white-space: nowrap;
    animation: typing 1s steps(20, end) forwards;
}
.article-card-box h1{
    font-size: 250%;
    color: #ffffff;
    font-weight: bold;
}
.article-card-box h3{
    font-size: 120%;
    color: #ffffff;
}
.article-card-box p{
    font-size: 80%;
    margin-bottom: 10px;
}
@keyframes typing {
    from{
        width: 0;
    }
    to{
        width: auto;
    }
}
</style>