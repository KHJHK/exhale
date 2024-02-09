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

	<div>
		<v-col
        v-for="(post, index) in posts"
        :key="post.id"
        cols="12"
        sm="6"
        md="4"
        lg="4"
		/>
	</div>
</template>

<script setup>
import { ref, computed, defineProps } from 'vue';
import { useCrudStore } from '@/stores/crud';
import { onMounted } from 'vue';
const { curPage, totalPage, ITEM_PER_PAGE, PAGE_PER_SECTION, setCurrentPage } = useCrudStore();
const leftmostPage = ref(1);
const posts = ([])
onMounted(() => {
	posts.value = props.posts
})
const props = defineProps(['posts'])
const getPaginationArray = (left) => {
	const res = [];
	for (let i = left; i < Math.min(totalPage + 1, left + PAGE_PER_SECTION); i++) {
		res.push(i);
	}
	return res;
};

const onChangeCurPage = (page) => {
	setCurrentPage(page)
};

const perPage = ref(10);


</script>

<style lang="scss" scoped>
@import "@/assets/scss/components/_pagination.scss";

</style>

  