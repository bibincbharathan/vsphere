pipeline {
    agent any

    parameters {
        string(name: 'PYTHON_PATH', defaultValue: '/Users/bbharathan/opt/anaconda3/bin/python', description: 'Path to Python executable')
        string(name: 'SCRIPT_PATH', defaultValue: 'getallvms_v2.py', description: 'Path to Python script')
        string(name: 'HOST', defaultValue: 'wdc-haas-vc02.oc.vmware.com', description: 'VC')
        string(name: 'USER', defaultValue: 'administrator@vsphere.local', description: 'USER')
        string(name: 'PASSWORD', defaultValue: 'SvL9n9123!', description: 'Password')

    }

    stages {
        stage('Setup') {
            steps {
                script {
                    // Print current working directory
                    sh 'pwd'
                    sh '''
                        #git clone https://github.com/vmware/pyvmomi-community-samples
                        cd pyvmomi-community-samples/samples
                        python3 getallvms.py -s ${HOST} -u ${USER} -p ${PASSWORD} -o 443 -nossl
                    '''
                }
            }
        }

        stage('Run Script') {
            steps {
                script {
                    // Run the Python script
                    sh """
                        cd pyvmomi-community-samples/samples
                        python3 getallvms.py -s ${HOST} -u ${USER} -p ${PASSWORD} -o 443 -nossl
                    
                    """
                }
            }
        }
    }
}
