import os

filePath = '/Users/zealot/yizhou/data/alltxt'
file_list=[]
for i, j, k in os.walk(filePath):
    # print(i, j, k)
    index = 0
    while index < len(k):
        # file_path=str(i)+"/"+k[index]
        file_path=k[index]
        # print(file_path)
        file_list.append(file_path)
        index+=1
    break #非递归
print(len(file_list))
# exit(0)
# report_date,company_full_name,stock_code,company_short_name,report_year,report_type,file_name
output_path="/Users/zealot/yizhou/git/FinanceChatGLM/llm_demo/data_test/company_annual_reports.csv"
with open(output_path, "w", encoding="utf8") as f2:
    f2.write("report_date,company_full_name,stock_code,company_short_name,report_year,report_type,file_name" + '\n')
    for line in file_list:
        filename = line.split('.')[0]
        tmp = ",".join(filename.split('__'))
        f2.write(str(tmp)+","+line + '\n')
        # logger.error(str(datetime.now()) + "\t" + f"qestion {index} is prompted")

        index+=1
    f2.flush()
    f2.close()