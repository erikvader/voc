import unittest

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):
    def test_pow_int_float_mix(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(0, 2.0))
            print(math.pow(2, 2))
            print(math.pow(2.0, 2))
            print(math.pow(2, 2.0))
        """)

    def test_pow_int(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(2, 2))
            print(math.pow(0, 2))
            print(math.pow(2, 0))
            print(math.pow(0, 0))
            print(math.pow(1, 2))
            """)

    def test_pow_float(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(2.0, 2.0))
            print(math.pow(0.0, 2.0))
            print(math.pow(2.0, 0.0))
            print(math.pow(0.0, 0.0))
            print(math.pow(1.0, 2.0))
        """)

    @unittest.expectedFailure
    def test_pow_negative_value(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(-2.0, 2.0))
        """)

    @unittest.expectedFailure
    def test_pow_string(self):
        self.assertCodeExecution("""
            import math
            print(math.pow("string", 2.0))
        """)

    @unittest.expectedFailure
    def test_pow_none(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(None, 2.0))
        """)
