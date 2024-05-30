'''
python 3.8; mmb; 12-Dec-2021

'''
from faker import Faker

def genPassword():
    '''
    ()->str
    generate 8-digit alpha numeric password that includes 6 letters and 2-numbers
    '''
    fk = Faker()
    astr = fk.lexify('??????')
    n = fk.random_int(0,5)
    bstr = astr[:n]+"%%"+astr[n:]
    password = fk.numerify(bstr)
    return password

def write_sat_users(recNum):
    '''
    (int)->none
    Populate 'sat_users.csv' file with name, email, password, department.
    id column is not populated since id will be autopopulated in database
    '''

    fk = Faker()

    with open("sat_users.csv", "w") as ref:
        for i in range(0, recNum):
            # fifth row is populated with known email and password for easy login as user. This user ID will be 1005
            #
            if i==4:
                row = "Mohammad Bhuiyan" + "," + "mmohinb@gmail.com" + "," + "mmb900" + "," + "Microbiology"
            else:
                row = fk.name() + "," + fk.free_email() + "," + genPassword() + "," + "Microbiology"



            ref.write(row+'\n')



def write_sat_scores(recNum):
    '''
    (int)->none
    populate sat_scores.csv with id, semester, semesterYear, crscode and mark
    id starts from 1001 to match with database specification
    '''

    fk = Faker()

    with open("sat_scores.csv", "w") as ref:
        id=10001 #first id

        for i in range(0, recNum):#number of student recNum
            sess = ['winter', 'spring', 'summer', 'fall', 'winter', 'spring', 'summer', 'fall'] # 8 courses should be distributed in 8 semester
            crscode = ['MB601', 'MB602', 'MB603', 'MB604', 'MB605', 'MB606', 'MB607', 'MB608']
            year = 1997 # No year before 1997; future years are calculated by 1997 + 'random integer between 1 to 9'
            c = 7 # decreasing counter to keep track of popping of sess and crscode array
            y = fk.random_digit_not_null() # 8 semester should be distributed in two consecutive years fk.random_int(year+y, year+y+1)
            for j in range(0, 8):#8 courses
                row = str(id) + "," + sess.pop(fk.random_int(0,c)) + "," + str(fk.random_int(year+y, year+y+1)) + "," + crscode.pop(fk.random_int(0,c)) + "," + str(fk.random_int(67,91))
                c=c-1
                ref.write(row+'\n')
            id = id+1

'''
Useful faker methods
>>> from faker import Faker as fk
>>> from faker import Faker
>>> fk = Faker()
>>> fk.name()
'Kimberly Robertson'
>>> fk.free_email()
'robin63@hotmail.com'
>>> fk.password()
'@$@d0As8s#'
>>> fk.random_digit_not_null()
4
>>> fk.random_int(50, 100, 5)
95
>>> fk.random_element(('x', 'y', 'z', 'k'))
'k'
>>> fk.random_element(('x', 'y', 'z', 'k'))
'x'
>>> fk.random_element(('x', 'y', 'z', 'k'))
'z'
>>> fk.lexify('a?b?c')
'aebjc'
>>> fk.lexify('?????')
'LOCWN'
>>> fk.numerify('%')
'6'
'''


if __name__ == "__main__":
    write_sat_users(500)
    write_sat_scores(500)
    pass
