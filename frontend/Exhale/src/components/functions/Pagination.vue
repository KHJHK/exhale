<template>
	<div style="display: flex; align-items: center;">
		<span>
			<a id="move"
				@click="
					() => {
                        if (leftmostPage > 1) {
						leftmostPage -= PAGE_PER_SECTION;
						onChangeCurPage(leftmostPage);
                        				}}
				"
				>&lt;</a
			>
		</span>
		<span class="page" v-for="page in getPaginationArray(leftmostPage)" :key="page">
			<a
				:class="{ on: page === curPage }"
				id="move"
				@click="
					() => {
						onChangeCurPage(page);
					}
				"
			>
				{{ page }}</a
			>
		</span>
		<span v-if="leftmostPage + PAGE_PER_SECTION <= totalPage">	
			<a
				id="move"
				@click="
					() => {
                        if (leftmostPage + PAGE_PER_SECTION <= totalPage){
						leftmostPage += PAGE_PER_SECTION;
						onChangeCurPage(leftmostPage);
					}}
				"
				>&gt;</a
			>
		</span>
	</div>

	
</template>

<script setup>
import { ref, computed, defineProps, defineEmits, watch, watchEffect } from 'vue';
import { useCrudStore } from '@/stores/crud';
import { onMounted } from 'vue';
import { boardList } from '@/api/boards';
import { storeToRefs } from 'pinia'

const crud = useCrudStore()
const {posts, curPage, tab, ITEM_PER_PAGE, PAGE_PER_SECTION, totalPage, isLoading} = storeToRefs(crud)


const leftmostPage = ref(1);

// const props = defineProps({
// 	curPage: Number,
// 	totalPage: Number,
// })
// const posts = ref(props.posts)
// const postsLength = posts.length
console.log('페이지네이션의 토탈', crud.totalPage)



const getPaginationArray = (left) => {
	const res = [];
	for (let i = left; i < Math.min(totalPage.value + 1, left + PAGE_PER_SECTION.value); i++) {
		res.push(i);
		
	}
	return res;
};

const onChangeCurPage = async (page) => {
  crud.curPage = page; // 현재 페이지 업데이트

  await crud.board_list()
}

// watch(() => totalPage, (newValue) => {
//   if (newValue !== undefined) {
//     totalPage.value = newValue;
//   }
// }, { immediate: true });

</script>

<style lang="scss" scoped>
@import "@/assets/scss/components/_pagination.scss";

</style>
