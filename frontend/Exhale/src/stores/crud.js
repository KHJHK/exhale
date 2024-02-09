import { defineStore } from "pinia";
import { useRouter } from "vue-router";
import {ref, computed, watch} from 'vue'



export const useCrudStore = defineStore('crud', ()=> {
    const router = useRouter()
    const tab = ref('')
    const curPage = ref(0)
    const ITEM_PER_PAGE = ref('');
    const PAGE_PER_SECTION = ref('');
    const totalPage = ref('')

    function setCurrentPage(newPage) {
        curPage.value = newPage;
    }
    watch (curPage,(newValue) =>{
        console.log(newValue)
    })
    watch (tab,(newValue)=>{
        if (newValue) {
        curPage.value = 0
        }
    })
return {tab, curPage, ITEM_PER_PAGE,PAGE_PER_SECTION,totalPage, setCurrentPage}
})