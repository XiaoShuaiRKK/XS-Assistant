<script setup>
import '../assets/Form/MainFormStyle.css'
import '@/static/js/Form/MainFormScript.js'

import BackgroundComponent from "@/components/Components/BackgroundComponent.vue";
import ProductCardWidget from "@/components/Components/Widget/ProductCardWidget.vue";
</script>


<template>
    <div class="main-title">
        <p>XS-Assistant</p>
    </div>
    <div class="main-container product">
        <div class="main-container-title">
            <span>P</span>
            <span>r</span>
            <span>o</span>
            <span>d</span>
            <span>u</span>
            <span>c</span>
            <span>t</span>
        </div>
        <div class="main-container card">
            <ProductCardWidget logo="../../assets/Img/icon/apple-fill-bk.png" title="XS-Assistant-IOS"
                class="main-container-widget-card" />
            <ProductCardWidget class="main-container-widget-card" />
            <ProductCardWidget class="main-container-widget-card" />
        </div>
    </div>
    <div class="main-container empty">

    </div>
    <div class="main-container assistant">
        <el-row :gutter="14" style="height: 25%;">
            <el-col :span="10">
                <div class="main-container-card assistant-title-card" ref="fadeElement"
                    :class="{ 'card-visible': isVisibleAssistant, 'card-hidden': !isVisibleAssistant }">
                    <h1>
                        每日推荐
                    </h1>
                    <el-carousel :interval="5000" arrow="always" style="padding: 5%;">
                        <el-carousel-item v-for="item in this.hotTopArticles" :key="item"
                            class="main-container-carousel-card">
                            <h2 style="font-weight: bold;">
                                {{ item.title }}
                            </h2>
                            <h3 style="color: #ffffff;">
                                {{ item.subTitle }}
                            </h3>
                            <h5>
                                {{ item.description }}
                            </h5>
                            <div style="overflow: hidden;">
                                <span>
                                    {{ item.context }}
                                </span>
                            </div>
                        </el-carousel-item>
                    </el-carousel>
                </div>
            </el-col>
            <el-col :span="8" style="margin-left: 5%;">
                <div class="main-container-card-title">
                    <p>
                        Assistant
                    </p>
                    <span>
                        一款能帮助，监督你的学习笔记助手,
                        这款笔记助手系统集成了智能化的管理和多平台同步功能，
                        帮助用户快速记录、整理和分类重要信息。不论是学习还是工作，
                        都可以轻松捕捉灵感，随时随地查阅笔记，确保信息高效利用。
                        同时，它提供丰富的编辑工具和标签功能，让每个笔记井然有序，助力用户提升效率。
                    </span>
                    <el-button class="main-container-card-btn">
                        Try Now
                    </el-button>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="14" style="max-height: 40%;margin-top: 30px;">
            <el-col :span="7" v-for="item in this.recommendations" :key="item.id">
                <home-article-card class="main-container-card" ref="fadeElement"
                    :class="{ 'card-visible': isVisibleAssistant, 'card-hidden': !isVisibleAssistant }" :article="item"/>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import store from "@/static/js/Util/store.js";
import { getArticleHotTop } from "@/static/js/Api/articleSearchApi.js";
import HomeArticleCard from '@/components/Components/card/HomeArticleCard.vue';

export default {
    components: {
        HomeArticleCard 
    },
    setup() {
    },
    mounted() {
        this.cardIntersectionAnime()
    },
    created() {
        if (store.getters.getUser !== null) {
            this.userInfo = store.getters.getUser
            console.log(this.userInfo)
        }
        this.topHotTopDataLoad()
        this.hopTopDataLoad()
    },
    data() {
        return {
            userInfo: {
                id: 0,
                firstName: '',
                lastName: '',
                email: '',
                birth: '',
                idNumber: '',
                areaId: 0
            },
            isVisibleAssistant: false,
            recommendationsParams: {
                target: '',
                page: 0,
                size: 3
            },
            recommendations: [],
            hotTopParams: {
                target: '',
                page: 0,
                size: 5
            },
            hotTopArticles: [],
            defaultArticleContent:{
                title: "Title",
                subTitle: "Sub title"
            }
        }
    },
    methods: {
        cardIntersectionAnime() {
            const observer = new IntersectionObserver((entries) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        this.isVisibleAssistant = true
                    }
                })
            })
            observer.observe(this.$refs.fadeElement)
        },
        async hopTopDataLoad(){
            await getArticleHotTop(this.recommendationsParams).then(res => {
                if(res.status === 200){
                    this.recommendations = res.data
                }
            })
        },
        async topHotTopDataLoad(){
            await getArticleHotTop(this.hotTopParams).then(res => {
                if(res.status === 200){
                    this.hotTopArticles = res.data
                }
            })
        }
    }
}
</script>

<style scoped>
.main-container-card {
    opacity: 0;
    transition: opacity 1s ease;
}

.card-visible {
    opacity: 1;
}

.card-hidden {
    opacity: 0;
}
.main-container-carousel-card h2{
    color: #ffffff;
}
</style>