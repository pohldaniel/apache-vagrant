import os

def get_env(secret_id, backup=None):
    return os.getenv(secret_id, backup)

if get_env('STAGE') == 'production':
    print("PRODUCTION")
    from .production import *
else:
    print("DEVELOPMENT")
    from .local import *