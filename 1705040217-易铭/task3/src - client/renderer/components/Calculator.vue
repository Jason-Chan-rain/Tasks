<template>
    <div >
        <md-table v-model="this.users" md-card>
            <md-table-toolbar>
                <h1 class="md-title">Users</h1>
            </md-table-toolbar>

            <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="ID" md-sort-by="id" md-numeric>{{ item.id }}</md-table-cell>
                <md-table-cell md-label="Name" md-sort-by="name">{{ item.name }}</md-table-cell>
                <md-table-cell md-label="salaryY" md-sort-by="salaryY">{{ item.salaryY }}</md-table-cell>
                <md-table-cell md-label="person" md-sort-by="person">{{ item.person }}</md-table-cell>
                <md-table-cell md-label="startY" md-sort-by="startY">{{ item.startY }}</md-table-cell>
                <md-table-cell md-label="newSalaryY" md-sort-by="newSalaryY">{{ item.newSalaryY }}</md-table-cell>
            </md-table-row>
        </md-table>
        <input type="file" ref="upload" accept=".xls,.xlsx" class="outputlist_upload">
    </div>
</template>

<script>
    import XLSX from 'xlsx'

    export default {
        name: "Calculator",
        data: () => ({
            users: [
            ],
            outputs: []
        }),
        mounted() {
            this.$refs.upload.addEventListener('change', e => {//绑定监听表格导入事件
                this.readExcel(e);
            })
        },
        methods: {
            readExcel(e) {
                let that = this;
                const files = e.target.files;
                console.log(files);
                if(files.length<=0){//if null name
                    return false;
                }else if(!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())){
                    this.$Message.error('Invalid Format');
                    return false;
                }

                const fileReader = new FileReader();
                fileReader.onload = (ev) => {
                    try {
                        const data = ev.target.result;
                        const workbook = XLSX.read(data, {
                            type: 'binary'
                        });
                        const wsname = workbook.SheetNames[0];//get first sheet
                        const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]);//generate json
                        console.log(ws);
                        this.users = [];//clear data
                        for(let i= 0;i<ws.length;i++){
                            let sheetData = {
                                address: ws[i].addr,
                                value: ws[i].value
                            }
                            this.users.push(sheetData);
                        }
                        this.$refs.upload.value = '';

                    } catch (e) {
                        return false;
                    }
                };
                fileReader.readAsBinaryString(files[0]);
                console.log(this.users);
                this.$forceUpdate();
            }
        },
    }
</script>

<style scoped>

</style>
