from sqlalchemy import create_engine, MetaData, Table, String, Column, Integer, text, Float
import pandas as pd
from llm_demo.util.transfer_to_excel import pad_stock_codes

# DATABASE_URL = "postgresql://dbusername:dbpassword@ip:port/db"
DATABASE_URL = "postgresql://postgres:123456@localhost:5432/hello_db"
FILE_PATH = "../data_test2/company_annual_reports.csv"
# report_date	company_full_name	stock_code	company_short_name	report_year	report_type	file_name
# df = pd.read_excel(FILE_PATH)
df = pd.read_csv(FILE_PATH)
df["stock_code"] = pad_stock_codes(df["stock_code"])
engine = create_engine(DATABASE_URL)
df.to_sql('company_annual_reports', engine, if_exists='replace', index=False,)