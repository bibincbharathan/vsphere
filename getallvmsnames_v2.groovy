pipeline {
    agent any

    parameters {
        string(name: 'PYTHON_PATH', defaultValue: '/Users/bbharathan/opt/anaconda3/bin/python', description: 'Path to Python executable')
        string(name: 'SCRIPT_PATH', defaultValue: 'getallvms_v2.py', description: 'Path to Python script')
        string(name: 'HOST', defaultValue: '10.187.96.82', description: 'VC')
        string(name: 'USER', defaultValue: 'administrator@vsphere.local', description: 'USER')
        string(name: 'PASSWORD', defaultValue: 'Artvm@123', description: 'Password')

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
                        python3 get_vm_names.py -s ${HOST} -u ${USER} -p ${PASSWORD} -o 443 -nossl
                    '''

                    // Set the PATH to include the Anaconda3 Python path
                    ENV['PATH'] = "${params.PYTHON_PATH}:${ENV['PATH']}"

                    // Install the pyVmomi Python package using pip3
                    sh 'pip3 install pyVmomi'
                }
            }
        }

        stage('Run Script') {
            steps {
                script {
                    // Run the Python script
                    sh """
                        cd pyvmomi-community-samples/samples
                        python3 get_vm_names.py -s ${HOST} -u ${USER} -p ${PASSWORD} -o 443 -nossl
                    
                    """
                }
            }
        }
    }
}
