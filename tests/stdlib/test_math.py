import unittest

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):
    def test_fabs(self):
        self.assertCodeExecution("""
            import math
            print(math.fabs(2.0))
            print(math.fabs(-2.0))
            print(math.fabs(0.0))
            """)

    @unittest.expectedFailure
    def test_fabs_wrong_arg_string(self):
        self.assertCodeExecution("""
            import math
            print(math.fabs("hej"))
        """)

    @unittest.expectedFailure
    def test_fabs_wrong_arg_int(self):
        self.assertCodeExecution("""
            import math
            print(math.fabs(0))
        """)
